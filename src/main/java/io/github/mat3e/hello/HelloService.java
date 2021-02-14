package io.github.mat3e.hello;

import io.github.mat3e.lang.Lang;
import io.github.mat3e.lang.LangRepository;

import java.util.Optional;

class HelloService {
    static final String FALLBACK_NAME = "world";
    static final Lang FALLBACK_LANG = new Lang(1, "Hello", "en");
    //private final Logger logger = (Logger) LoggerFactory.getLogger(HelloService.class);

    private LangRepository repository;

    HelloService() {
        this(new LangRepository());
    }

    HelloService(LangRepository repository){
        this.repository = repository;
    }

    String prepareGreeting(String name, String lang) {
        //the parameter passed to the findById (that is what in the String lang - second parameter in the line above)
        // is of type Long so we have to convert  it from string to Long
        Integer langId;
        try {
            langId = Optional.ofNullable(lang).map(Integer::valueOf).orElse(FALLBACK_LANG.getId()); //Long::valueOf returns passed String as Long
        } catch(NumberFormatException e) {
            //logger.info("Non-numeric language is used" + lang);
            langId = FALLBACK_LANG.getId();
        }
        var welcomeMsg = repository.findById(langId).orElse(FALLBACK_LANG).getWelcomeMsg();
        var nameToWelcome = Optional.ofNullable(name).orElse(FALLBACK_NAME);

        return welcomeMsg + " " + nameToWelcome;
    }
}
