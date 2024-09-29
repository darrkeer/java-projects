package markup;

import java.util.List;

public class ListItem extends AbstractMarkup{
    public ListItem(List<IterableMarkup> content) {
        super(List.copyOf(content));
    }

    @Override
    public String getMarkdownMarkup() { // NOTE: StringBuilder.append(): "If str is null, then the four characters "null" are appended."
        return null;
    }
 
    @Override
    public String getBBCodePrefix() {
        return "[*]";
    }

    @Override
    public String getBBCodeSuffix() {
        return "";
    }
}
