package entity.util;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;
public class JsonParser {
    private JSONObject data;
    public JsonParser(String path){
        setData(path);
    }
    /**
     * read the file of the given path
     * @param path the file path
     * @return a string of what the file contains
     */
    private String fileReader(String path)
    {
        String res = "";
        try
        {
            res = Files.readString(Paths.get(path));
        }
        catch(IOException e)
        {
            System.out.println("file not found");
        }
        return res;
    }
    //! utile ?
    /**
     * Get the JSONArray of name arrayName from the Json file
     * @param arrayName The name of the array to retrieve.
     * @return The JSONArray object.
     */
    public JSONArray getJSONArrayFromData(String arrayName) {
        JSONArray res = this.data.getJSONArray(arrayName);
        return res;
    }
    
    //! utile ?
    /**
     * Get the JSON object with the given name from the JSON data
     * @param objectName The name of the object to be retrieved.
     * @return The JSONObject that is stored in the data field of the JSONObject.
     */
    public JSONObject getJSONObjectFromData(String objectName) {
        JSONObject res = this.data.getJSONObject(objectName);
        return res;
    }
    /**
     * This function returns the data JSONObject
     * @return JSONObject.
     */
    public JSONObject getData() {return this.data;}
    /**
     * Reads a file and parse it into JSONObject
     * @param path The path to the file that contains the JSON data.
     */
    public void setData(String path)
    {
        String strData = fileReader(path);
        this.data = new JSONObject(strData);
    }
    
    /**
     * It sets the data JSONobject to the dataObject parameter.
     * @param dataObject The JSON object that contains the data.
     */
    public void setData(JSONObject dataObject) {this.data = dataObject;}
}