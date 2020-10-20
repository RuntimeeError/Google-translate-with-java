package utility.json_parsing;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.simple.JSONObject;
import utility.Constants.Constants;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ProcessJson {
    private String[] doNotTranslate = Constants.doNotTranslate;
    private List<String> list = Arrays.asList(doNotTranslate);

    public void processJson(String jsonStr) throws IOException {
        try {
            jsonStringToMap(jsonStr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void jsonStringToMap(String str) throws IOException {
        HashMap<String, Object> map = new HashMap();
        ObjectMapper mapper = new ObjectMapper();

        map = mapper.readValue(str, new TypeReference<HashMap>() {
        });
        HashMap hashMap = translateData(map);
        // String json = new ObjectMapper().writeValueAsString(hashMap);
        try {
            writeToFile(hashMap);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private HashMap translateData(HashMap<String, Object> map) {
        for (String o : map.keySet()) {
            if (map.get(o) instanceof HashMap) {
                translateData((HashMap<String, Object>) map.get(o));
            } else {
                if (list.contains(map.get(o))) {
                    Client client = new Client();
                    String str = client.translate(map.get(o).toString());
                    map.put(o, str);
                }
            }
        }
        return map;
    }

    private void writeToFile(HashMap hashMap) throws IOException, JSONException {
        JSONObject jsonObject = new JSONObject(hashMap);
        FileWriter file = new FileWriter(Constants.outputFilePath);
        file.write(jsonObject.toJSONString());
        file.close();
    }
}

