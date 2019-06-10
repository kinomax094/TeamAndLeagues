package pl.com.b2bnetwork;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
@ComponentScan(basePackages = {"pl.com.b2bnetwork.football", "pl.com.b2bnetwork.football.service"})
public class TestContext {
}
