package com.hogp.testingeverything.kotlin;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Meeting1 {
    private String title;

    public void addTitle(@NotNull String title) {
        this.title = title;
    }

    public @Nullable
    String meetingTitle() {
        return title;
    }
}
