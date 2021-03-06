package org.kefirsf.bb.conf;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Tests for {@link Configuration}.
 *
 * @author kefir
 */
public class ConfigurationTest {
    @Test
    public void testScope(){
        Configuration conf = new Configuration();

        // Fill the configuration
        // Scope
        Scope root = new Scope(Scope.ROOT);
        conf.setRootScope(root);

        // Read properties
        Assert.assertEquals(root, conf.getRootScope());
    }

    @Test
    public void testPrefix(){
        Configuration conf = new Configuration();
        Assert.assertNotNull(conf.getPrefix());

        // Prefix
        Template prefix = new Template(Arrays.asList(new Constant("test prefix")));
        conf.setPrefix(prefix);

        Assert.assertEquals(prefix, conf.getPrefix());
    }

    @Test
    public void testSuffix(){
        Configuration conf = new Configuration();
        Assert.assertNotNull(conf.getSuffix());

        // Suffix
        Template suffix  = new Template(Arrays.asList(new Constant("test suffix")));
        conf.setSuffix(suffix);

        Assert.assertEquals(suffix, conf.getSuffix());
    }

    @Test
    public void testParams(){
        Configuration conf = new Configuration();

        Assert.assertNotNull(conf.getParams());

        Map<String, CharSequence> params = new HashMap<String, CharSequence>();
        params.put("param 1", "value 1");
        params.put("param 2", "value 2");
        conf.setParams(params);

        Assert.assertEquals(params, conf.getParams());
    }

    @Test
    public void testNestingLimit(){
        Configuration conf = new Configuration();
        Assert.assertEquals(Configuration.DEFAULT_NESTING_LIMIT, conf.getNestingLimit());
        int nestingLimit = 10;
        conf.setNestingLimit(nestingLimit);
        Assert.assertEquals(nestingLimit, conf.getNestingLimit());
    }

    @Test
    public void testPropagateNestingException(){
        Configuration conf = new Configuration();
        Assert.assertEquals(Configuration.DEFAULT_PROPAGATE_NESTING_EXCEPTION, conf.isPropagateNestingException());
        conf.setPropagateNestingException(true);
        Assert.assertTrue(conf.isPropagateNestingException());
    }
}
