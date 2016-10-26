package com.antweb.alexaasciiconverter;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SsmlOutputSpeech;

public class AsciiConverterSpeechlet implements Speechlet {

    @Override
    public void onSessionStarted(SessionStartedRequest sessionStartedRequest, Session session) throws SpeechletException {

    }

    @Override
    public SpeechletResponse onLaunch(LaunchRequest launchRequest, Session session) throws SpeechletException {
        PlainTextOutputSpeech response = new PlainTextOutputSpeech();
        response.setText("Which character or code would you like to convert?");
        return SpeechletResponse.newTellResponse(response);
    }

    @Override
    public SpeechletResponse onIntent(IntentRequest intentRequest, Session session) throws SpeechletException {
        Intent intent = intentRequest.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;

        if ("CharToAsciiIntent".equals(intentName)) {
            return getCharToAsciiResponse(intentRequest);
        } else if ("AsciiToCharIntent".equals(intentName)) {
            return getAsciiToCharResponse(intentRequest);
        } else {
            PlainTextOutputSpeech response = new PlainTextOutputSpeech();
            response.setText("Could you repeat that?");

            Reprompt reprompt = new Reprompt();
            reprompt.setOutputSpeech(response);

            return SpeechletResponse.newAskResponse(response, reprompt);
        }
    }

    @Override
    public void onSessionEnded(SessionEndedRequest sessionEndedRequest, Session session) throws SpeechletException {

    }

    private SpeechletResponse getCharToAsciiResponse(IntentRequest intentRequest) {
        char inputChar = 'B';
        String asciiCodeLower = charToAscii(Character.toLowerCase(inputChar));
        String asciiCodeUpper = charToAscii(Character.toUpperCase(inputChar));

        SsmlOutputSpeech response = new SsmlOutputSpeech();
        String ssml = "<speak>The <phoneme alphabet=\"ipa\" ph=\"æski\">ascii</phoneme>" +
                "code for the character <break strength=\"medium\"/> " +
                "<say-as interpret-as=\"characters\">" + inputChar + "</say-as><break strength=\"medium\"/> " +
                "is <break strength=\"medium\"/>" + asciiCodeLower + " in lower case " +
                "and <break strength=\"medium\"/>" + asciiCodeUpper + " in upper case.</speak>";
        response.setSsml(ssml);
        return SpeechletResponse.newTellResponse(response);
    }

    private SpeechletResponse getAsciiToCharResponse(IntentRequest intentRequest) {
        int inputAscii = 84;
        String lowerUpperCaseStr = "";

        if (inputAscii >= 65 && inputAscii <= 90) {
            lowerUpperCaseStr = "upper case ";
        } else if (inputAscii >= 97 && inputAscii <= 122) {
            lowerUpperCaseStr = "lower case ";
        }

        String character = asciiToChar(inputAscii);

        SsmlOutputSpeech response = new SsmlOutputSpeech();
        String ssml = "<speak>The character for <phoneme alphabet=\"ipa\" ph=\"æski\">ascii</phoneme> " +
                "code " + inputAscii + " <break strength=\"medium\"/> is " + lowerUpperCaseStr + character +
                ".</speak>";
        response.setSsml(ssml);
        return SpeechletResponse.newTellResponse(response);
    }

    private String charToAscii(char c) {
        return new Integer(c).toString();
    }

    private String asciiToChar(int ascii) {
        return Character.toString((char) ascii);
    }
}
