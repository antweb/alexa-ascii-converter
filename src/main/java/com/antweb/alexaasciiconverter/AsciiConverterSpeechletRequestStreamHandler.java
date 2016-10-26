package com.antweb.alexaasciiconverter;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;
import java.util.Set;

public class AsciiConverterSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
    private static final Set<String> supportedApplicationIds = new HashSet<String>();
    static {
        supportedApplicationIds.add("amzn1.ask.skill.c964a27b-8316-4d27-aec6-cab9f2f86866");
    }

    public AsciiConverterSpeechletRequestStreamHandler() {
        super(new AsciiConverterSpeechlet(), supportedApplicationIds);
    }
}
