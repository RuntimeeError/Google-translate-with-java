package utility.json_parsing;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

import java.io.IOException;

public class Client {
    public String translate(String str) {


        //Translate utility.translate = TranslateOptions.getDefaultInstance().getService();
        Translate translate = TranslateOptions.newBuilder().setApiKey("set your key here").build().getService();
        Translation translation = translate.translate(
                str,
                Translate.TranslateOption.sourceLanguage("en"),
                Translate.TranslateOption.targetLanguage("ja"));
        return translation.getTranslatedText();
    }
}
