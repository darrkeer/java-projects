package markup;

import java.util.List;

public class UnorderedList extends AbstractMarkup implements IterableMarkup{
    public UnorderedList(List<ListItem> content) {
        super(List.copyOf(content));
    }

    @Override
    public String getMarkdownMarkup() {
        return null;
    }  // NOTE: StringBuilder.append(): "If str is null, then the four characters "null" are appended."

    @Override
    public String getBBCodePrefix() {
        return "[list]";
    }

    @Override
    public String getBBCodeSuffix() {
        return "[/list]";
    }
}
