package org.ken.avery.collections;

/*
 * Copyright (c) 2010, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deal
{
    public static void main(final String[] args)
    {
        //        if (args.length < 2)
        //        {
        //            System.out.println("Usage: Deal hands cards");
        //            return;
        //        }
        //    int numHands = Integer.parseInt(args[0]);
        //    int cardsPerHand = Integer.parseInt(args[1]);

        final int numHands = 4;
        final int cardsPerHand = 5;

        // Make a normal 52-card deck.
        final String[] suit = new String[] {
                "spades", "hearts", "diamonds", "clubs"
        };
        final String[] rank = new String[] {
                "ace", "2", "3", "4", "5", "6", "7", "8",
                "9", "10", "jack", "queen", "king"
        };
        final List<String> deck = new ArrayList<String>();
        for (final String element : suit)
        {
            for (final String element2 : rank)
            {
                deck.add(element2 + " of " + element);
            }
        }

        // Shuffle the deck.
        Collections.shuffle(deck);

        if (numHands * cardsPerHand > deck.size())
        {
            System.out.println("Not enough cards.");
            return;
        }

        for (int i = 0; i < numHands; i++)
        {
            System.out.println(dealHand(deck, cardsPerHand));
        }
    }

    public static <E> List<E> dealHand(final List<E> deck, final int n)
    {
        final int deckSize = deck.size();
        final List<E> handView = deck.subList(deckSize - n, deckSize);
        final List<E> hand = new ArrayList<E>(handView);
        handView.clear();
        return hand;
    }
}
