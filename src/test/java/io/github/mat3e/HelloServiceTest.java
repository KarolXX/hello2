package io.github.mat3e;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {
    private static final String WELCOME = "Hello";
    private static final String FALLBACK_ID_WELCOME = "Hola";

    @Test
    public void test_prepareGreeting_nullName_returnsGreetingWithFallbackName() {
        // given
        String name = null;
        var SUT = new HelloService(alwaysReturningHelloRepository());

        // when
        var result = SUT.prepareGreeting(null, "-1");

        // then
        assertEquals("Hello " + HelloService.FALLBACK_NAME, result);
    }

    @Test
    public void test_prepareGreeting_name_returnsGreetingWithName() {
        // given
        var name = "test";
        var SUT = new HelloService(alwaysReturningHelloRepository());

        // when
        var result = SUT.prepareGreeting(name, "-1");

        // then
        assertEquals("Hello " + name, result);
    }

    @Test
    public void test_prepareGreeting_nonExistingLang_returnsGreetingWithFallbackLang() {
        // given
        var mockRepository = new LangRepository() {
                @Override
                Optional<Lang> findById(Long id) {
                    return Optional.empty();
            };
        };
        var SUT = new HelloService(mockRepository);

        // when
        var result = SUT.prepareGreeting(null, "4");

        // then
        assertEquals(HelloService.FALLBACK_LANG.getWelcomeMsg() + " " + HelloService.FALLBACK_NAME, result);
    }

    @Test
    public void test_prepareGreeting_nullLang_returnsGreetingWithFallbackIdLang() {
        // given
        var SUT = new HelloService(fallbackLangIdRepository());

        // when
        var result = SUT.prepareGreeting(null, null);

        // then
        assertEquals(WELCOME + " " + HelloService.FALLBACK_NAME, result);
    }

    @Test
    public void test_prepareGreeting_textLang_returnsGreetingWithFallbackIdLang() {
        // given
        var SUT = new HelloService(fallbackLangIdRepository());

        // when
        var result = SUT.prepareGreeting(null, "abc");

        // then
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME, result);
    }

    //by implementing the following functions that returns LangRepository with
    //overwritten method we save our test as unit because if there is an error
    //in the repository our test will run
    private LangRepository fallbackLangIdRepository() {
        return new LangRepository(){
            @Override
            Optional<Lang> findById(Long id) {
                if(id.equals(HelloService.FALLBACK_LANG.getId())) {
                    return Optional.of(new Lang(null, FALLBACK_ID_WELCOME, null));
                }
                return Optional.empty();
            }
        };
    }

    private LangRepository alwaysReturningHelloRepository() {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Long id) {
                return Optional.of(new Lang(null, WELCOME, null));
            }
        };
    }


}
