package io.github.shniu.springbootexitcode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeEvent;
import org.springframework.boot.ExitCodeExceptionMapper;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

/**
 * https://fangjian0423.github.io/2017/06/28/springboot-application-exit/
 * https://juejin.im/post/5c93a576e51d4574d019cf7d
 */

@SpringBootApplication
@Slf4j
public class SpringbootExitcodeApplication implements ExitCodeGenerator {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringbootExitcodeApplication.class, args);

		// 1. 包裹 exit
		System.exit(SpringApplication.exit(context));
	}

	// 实现 ExitCodeGenerator
	@Override
	public int getExitCode() {
		return 10;
	}

	@Bean
	public CommandLineRunner createException() {
		return args -> Integer.parseInt("1");
	}

	// 定制错误码，根据不同类型的 Exception 来返回不同的错误码
	@Bean
	public ExitCodeExceptionMapper exitCodeToExceptionMapper() {
		return exception -> {
			if (exception.getCause() instanceof NumberFormatException) {
				return 80;
			}

			return 1;
		};
	}

	// 增加一个监听器，来监听退出事件
	@Bean
	public DemoListener demoListener() {
		return new DemoListener();
	}


	private static class DemoListener {

		@EventListener
		public void exitCode(ExitCodeEvent event) {
			log.info("Exit code {}", event.getExitCode());
			log.info("遇到某种类型的错误码退出时，我可以在这里定制一些动作，比如发送一条消息，或者" +
					"做一次远程调用，或者方案数据库记录错误日志等");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// ...
			}
		}
	}
}
