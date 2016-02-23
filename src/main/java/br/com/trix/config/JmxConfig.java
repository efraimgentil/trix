package br.com.trix.config;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;

import java.util.concurrent.TimeUnit;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 22/02/16.
 */
@Configuration
@EnableMBeanExport
@EnableMetrics
public class JmxConfig extends MetricsConfigurerAdapter {

  @Override
  public void configureReporters(MetricRegistry metricRegistry) {
    // registerReporter allows the MetricsConfigurerAdapter to
    // shut down the reporter when the Spring context is closed
    registerReporter(JmxReporter
            .forRegistry(metricRegistry)
            .build()).start();
  }
}
