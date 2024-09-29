package markup;

public interface MarkupElement {

    void toMarkdown(StringBuilder s);

    void toBBCode(StringBuilder s);

    String getMarkdownMarkup(); // NOTE: It could be abstract method in Abstract*

    String getBBCodePrefix(); // NOTE: It could be abstract method in Abstract*

    String getBBCodeSuffix(); // NOTE: It could be abstract method in Abstract*
}
