import textblocks.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Main {
    public static void main(String[] args) {
        long id = 123456;

        // Each TextBlock consists of several FormatTags, hence we start with a list
        List<FormatTag> tagList = new ArrayList<>();

        tagList.add(
                new FormatTag(
                        // Each format tag has a type of value associated with it
                        // We must initialise an empty value here, because static methods in java cannot be abstract
                        // and our value objects are responsible for creating their html input fields and verifying
                        // the type of the response values
                        new MessageFormatTagValue(),
                        "message"
                )
        );
        tagList.add(new FormatTag(new TextFormatTagValue(), "name"));
        TextBlock block = new TextBlock(id, "Message: ${message} \n Name: ${name}", tagList);

        Map<String, String> requestValues = new HashMap<>();

        requestValues.put(id + "-name", "Jonny");
        requestValues.put(id + "-message", "Go there");

        TextBlockValue val = block.fromForm(requestValues);

        System.out.println(block);
        System.out.println(block.asForm());
        System.out.println(val);
    }
}
