package testdemo;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

//Guice module to inject port variable in stub
public class StubModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Integer.class)
                .annotatedWith(Names.named("PORT"))
                .toInstance(7777);
    }
}
