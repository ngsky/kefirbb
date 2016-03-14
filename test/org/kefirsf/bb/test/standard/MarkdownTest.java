package org.kefirsf.bb.test.standard;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.kefirsf.bb.BBProcessorFactory;
import org.kefirsf.bb.TextProcessor;

import java.util.Arrays;
import java.util.Collection;

import static org.kefirsf.bb.ConfigurationFactory.MARKDOWN_CONFIGURATION_FILE;
import static org.kefirsf.bb.test.Assert.assertProcess;

/**
 * Tests for the standard markdown configuration.
 * @author kefir
 */
@RunWith(Parameterized.class)
public class MarkdownTest {
    private TextProcessor processor;

    @Parameterized.Parameters(name = "{1}")
    public static Collection<String[]> data(){
        return Arrays.asList(
                new String[][]{
                        {"<p>Simple text paragraph.</p>", "Simple text paragraph."},
                        {"<p>First paragraph.</p><p>Second paragraph.</p>", "First paragraph.\n\nSecond paragraph."},
                        {"<p>Paragraph with an\n unsigned line break.</p>", "Paragraph with an\n unsigned line break."},
                        {"<p>Paragraph with a signed<br/>line break.</p>", "Paragraph with a signed  \nline break."},
                        {"<h1>This is an H1</h1>", "This is an H1\n============="},
                        {"<h2>This is an H2</h2>", "This is an H2\n-------------"},
                        {"<h1>This is an H1</h1>", "# This is an H1"},
                        {"<h2>This is an H2</h2>", "## This is an H2"},
                        {"<h6>This is an H6</h6>", "###### This is an H6"},
                        {"<h1>This is an H1</h1>", "# This is an H1 #"},
                        {"<h2>This is an H2</h2>", "## This is an H2 ##"},
                        {"<h3>This is an H3</h3>", "### This is an H3 ######"},
                        {"<hr/>", "* * *"},
                        {"<hr/>", "***"},
                        {"<hr/>", "*****"},
                        {"<hr/>", "- - -"},
                        {"<hr/>", "---------------------------------------"},
                        {"<p>\\`*_{}[]()#+-.!</p>", "\\\\`\\*\\_\\{\\}\\[\\]\\(\\)\\#\\+\\-\\.\\!"}
                }
        );
    }

    private String expected;
    private String source;

    public MarkdownTest(String expected, String actual) {
        this.expected = expected;
        this.source = actual;
    }

    @Before
    public void init(){
        BBProcessorFactory processorFactory = BBProcessorFactory.getInstance();
        processor = processorFactory.createFromResource(MARKDOWN_CONFIGURATION_FILE);
    }

    @Test
    public void testExpression(){
        assertProcess(processor, expected, source);
    }
}
