package com.LOOP.main;

import fileio.FileSystem;

import java.io.IOException;

public class Main {

    public static void main(final String[] args) throws IOException {
        FileSystem fs = new FileSystem(args[0], args[1]);
    }
}
