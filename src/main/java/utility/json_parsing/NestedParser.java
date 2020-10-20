package utility.json_parsing;


import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import utility.Constants.Constants;

import java.io.FileInputStream;
import java.io.IOException;

public class NestedParser {
    public static void main(String[] args) throws IOException, JSONException {
        FileInputStream fis = new FileInputStream(Constants.readFileFromPath);
        String data = IOUtils.toString(fis, "UTF-8");
        ProcessJson processJson = new ProcessJson();
        processJson.processJson(data);
    }
}






