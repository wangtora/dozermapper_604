package jp.co.wangtora;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.RestAssured;

import jp.co.wangtora.model.A;
import jp.co.wangtora.model.B;
import jp.co.wangtora.model.S;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class, value = {"server.port:0",
		"spring.datasource.url:jdbc:h2:mem:dozermapper_604;DB_CLOSE_ON_EXIT=FALSE"})
@WebAppConfiguration
public class HelloControllerTest {

	@Value("${local.server.port}")
	int port;

	@Autowired
	HelloController controller;

	@Before
	public void setUp() throws Exception {

		RestAssured.port = port;
	}

	@Test
	public void testHello() throws Exception {

		when().get("/").then()
				.body(is("Hello World!"));
	}

	@Test
	public void testCalc() throws Exception {

		given().param("left", 100)
				.param("right", 200)
				.get("/calc")
				.then()
				.body("left", is(100))
				.body("right", is(200))
				.body("answer", is(300));
	}

	@Test
	public void testDozermapper_604() throws Exception {

		B b = new B();
		b.setSourceAsString("{}");
		A a = new A();
		a.setB(b);
		S s = controller.dozermapper_604(a);
		assertEquals(s.getT().getSource(), b.getSourceAsString());
	}
}