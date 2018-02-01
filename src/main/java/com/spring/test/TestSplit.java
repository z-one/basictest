package com.spring.test;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TestSplit
{
    /**
     * The token that indicates the start of an XML comment.
     */
    private static final String START_COMMENT = "<!--";

    /**
     * The token that indicates the end of an XML comment.
     */
    private static final String END_COMMENT = "-->";

    /**
     * Indicates whether or not the current parse position is inside an XML comment.
     */
    private boolean inComment;

    public static void main(String[] args)
    {

        String str = "1,2,3,,";
        String[] arr1 = str.split(",");
        String[] arr2 = str.split(",", -1);

        // equals ?false
        System.out.println(Arrays.deepEquals(arr1, arr2));

        // values [1, 2, 3]
        System.out.println(Arrays.deepToString(arr1));

        // [1, 2, 3, , ]
        System.out.println(Arrays.deepToString(arr2));

        String xmlStr = "<!-- test -->";
        TestSplit test = new TestSplit();
        System.out.println(test.consumeCommentTokens(xmlStr));
    }

    public List<String> readFile(String path)
    {
        List<String> list = new ArrayList<String>();

        String content = "This is the content to write into file";

        System.out.println("Done");
        File file = new File("/users/mkyong/filename.txt");
        try (FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);)
        {
            if (!file.exists())
            {
                file.createNewFile();
            }
            bw.write(content);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }

    private String consumeCommentTokens(String line)
    {
        if (!line.contains(START_COMMENT) && !line.contains(END_COMMENT))
        {
            return line;
        }
        while ((line = consume(line)) != null)
        {
            if (!this.inComment && !line.trim().startsWith(START_COMMENT))
            {
                return line;
            }
        }
        return line;
    }

    /**
     * Consume the next comment token, update the "inComment" flag and return the remaining
     * content.
     */
    private String consume(String line)
    {
        int index = (this.inComment ? endComment(line) : startComment(line));
        return (index == -1 ? null : line.substring(index));
    }

    /**
     * Try to consume the {@link #START_COMMENT} token.
     * 
     * @see #commentToken(String, String, boolean)
     */
    private int startComment(String line)
    {
        return commentToken(line, START_COMMENT, true);
    }

    private int endComment(String line)
    {
        return commentToken(line, END_COMMENT, false);
    }

    /**
     * Try to consume the supplied token against the supplied content and update the in comment
     * parse state to the supplied value. Returns the index into the content which is after the
     * token or -1 if the token is not found.
     */
    private int commentToken(String line, String token, boolean inCommentIfPresent)
    {
        int index = line.indexOf(token);
        if (index > -1)
        {
            this.inComment = inCommentIfPresent;
        }
        return (index == -1 ? index : index + token.length());
    }
}
