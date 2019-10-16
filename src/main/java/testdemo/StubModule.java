package testdemo;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class StubModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Integer.class)
                .annotatedWith(Names.named("PORT"))
                .toInstance(7777);
    }
}
