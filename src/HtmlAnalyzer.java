import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class HtmlAnalyzer {
    public static void main(String[] args) throws Exception {
        URL u;
        Scanner sc;
        try
        {
        u = new URL(args[0]);
        sc = new Scanner(u.openStream());
        }catch (Exception e)
        {
            System.out.println("URL connection error");
            return;
        }

        StringBuilder html = new StringBuilder();
        while (sc.hasNext())
        {
            html.append(sc.nextLine()); //receiving HTML
        }
        sc.close(); //prevent resource leaks

        String deepestLevelText = "";
        int maxDepth = 0;
        HtmlTokenizer tokenizer = new HtmlTokenizer(html);
        List<String> tokens = tokenizer.tokenize();
        Stack<String> depthStack = new Stack<String>();
        for (String token : tokens)
        {
            if(token.charAt(0) == '<')
            {
                if (token.charAt(1) == '/')
                {
                    String tagValue = token.substring(2, token.length() - 1);
                    String lastTag = depthStack.peek().substring(1, depthStack.peek().length() - 1);
                    if (lastTag.equals(tagValue)) //checking if the tag has been closed correctly
                    {
                        depthStack.pop();
                    }
                    else
                    {
                        System.out.println("malformed HTML");
                        return;
                    }
                }
                else depthStack.add(token);
            }
            else
            {
                if (depthStack.size() > maxDepth) //checking if the current text is deeper than the last "deepest"
                {
                    maxDepth = depthStack.size();
                    deepestLevelText = token;
                }
            }
        }
        if (depthStack.isEmpty()) System.out.println(deepestLevelText);
        else System.out.println("malformed HTML");
    }

}
class HtmlTokenizer
{
    private StringBuilder html;
    private int index = 0;

    public HtmlTokenizer(StringBuilder html)
    {
        this.html = html;
    }
    public List<String> tokenize() {
        List<String> tokens = new ArrayList<>();
        while (index < html.length()) {
            if (html.charAt(index) == '<') {
                // start of a tag
                String tag = readTag();
                if (tag != null) {
                    tokens.add(tag);
                }
            } else {
                // read text content
                String text = readText();
                if (!text.isBlank()) {
                    tokens.add(text);
                }
            }
        }
        return tokens;
    }

    private String readTag() {
        int start = index;  // store start position
        index++; // skip '<'

        boolean isClosingTag = html.charAt(index) == '/';
        if (isClosingTag) index++; // skip '/'

        StringBuilder tagName = new StringBuilder();
        while (index < html.length() && Character.isLetterOrDigit(html.charAt(index)))
        {
            tagName.append(html.charAt(index));
            index++;
        }

        // ignore everything until the '>' character is reached
        while (index < html.length() && html.charAt(index) != '>')
        {
            index++;
        }
        index++; // skip '>'

        String tag = tagName.toString();

        return isClosingTag ? "</" + tag + ">" : "<" + tag + ">";
    }

    private String readText() {
        StringBuilder text = new StringBuilder();
        while (index < html.length() && html.charAt(index) != '<')
        {
            text.append(html.charAt(index));
            index++;
        }
        return text.toString().trim();
    }
}