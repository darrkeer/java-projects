package markup;

import java.util.List;

public class OrderedList extends AbstractMarkup implements IterableMarkup{
    public OrderedList(List<ListItem> content) {
        super(List.copyOf(content));
    }

    @Override
    public String getMarkdownMarkup() {
        return null;
    }  // NOTE: StringBuilder.append(): "If str is null, then the four characters "null" are appended."

    @Override
    public String getBBCodePrefix() {
        return "[list=1]";
    }

    @Override
    public String getBBCodeSuffix() {
        return "[/list]";
    }
}
