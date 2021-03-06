package org.ken.avery.networking;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
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

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReverseServlet extends HttpServlet
{
    private static final long serialVersionUID = -83449069833201736L;
    private static String message = "Error during Servlet processing";

    @Override
    public void doPost(final HttpServletRequest req, final HttpServletResponse resp)
    {
        try
        {
            final int len = req.getContentLength();
            final byte[] input = new byte[len];

            final ServletInputStream sin = req.getInputStream();
            int c, count = 0;
            while ((c = sin.read(input, count, input.length - count)) != -1)
            {
                count += c;
            }
            sin.close();

            final String inString = new String(input);
            final int index = inString.indexOf("=");
            if (index == -1)
            {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().print(message);
                resp.getWriter().close();
                return;
            }
            final String value = inString.substring(index + 1);

            //decode application/x-www-form-urlencoded string
            final String decodedString = URLDecoder.decode(value, "UTF-8");

            //reverse the String
            final String reverseStr = (new StringBuffer(decodedString)).reverse().toString();

            // set the response code and write the response data
            resp.setStatus(HttpServletResponse.SC_OK);
            final OutputStreamWriter writer = new OutputStreamWriter(resp.getOutputStream());

            writer.write(reverseStr);
            writer.flush();
            writer.close();
        }
        catch (final IOException e)
        {
            try
            {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().print(e.getMessage());
                resp.getWriter().close();
            }
            catch (final IOException ioe)
            {
            }
        }

    }

}
