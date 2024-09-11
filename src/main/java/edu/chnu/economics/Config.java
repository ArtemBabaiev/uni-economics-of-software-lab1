package edu.chnu.economics;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.chnu.economics.cocomo.models.CostDriversList;
import lombok.Getter;

@Configuration
@Getter
public class Config {

	private CostDriversList costDrivers;

	@Value("classpath:costDrivers.json")
	private void setCostDrivers(Resource resource) throws IOException {
		String content = resource.getContentAsString(Charset.defaultCharset());
		ObjectMapper pm = new ObjectMapper();
		costDrivers = pm.readValue(content, CostDriversList.class);
	}
}
