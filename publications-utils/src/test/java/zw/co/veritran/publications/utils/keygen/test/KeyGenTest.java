package zw.co.veritran.publications.utils.keygen.test;

import org.junit.Test;
import zw.co.veritran.publications.utils.keygen.KeyGen;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by tyamakura on 29/11/2016.
 */
public class KeyGenTest {

    @Test
    public void shouldGenerateNonNullKey() {
        final Long generatedKey = KeyGen.getUniqueId();
        assertThat(generatedKey, is(notNullValue()));

    }

    @Test
    public void shouldGenerate18CharacterKey() {
        final Long generatedKey = KeyGen.getUniqueId();
        assertThat(generatedKey.toString().length(), is(equalTo(18)));

    }

}
