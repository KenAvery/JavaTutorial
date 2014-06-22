package org.ken.avery.annotations;

public @interface ClassHeader
{
    String author();

    String date();

    String userStory();

    String description();

    String[] reviewers();

    boolean tested();
}
