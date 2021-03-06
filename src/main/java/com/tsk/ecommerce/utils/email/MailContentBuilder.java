package com.tsk.ecommerce.utils.email;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.tsk.ecommerce.entities.Customer;
import com.tsk.ecommerce.entities.OrderLine;
import com.tsk.ecommerce.entities.Orders;

@Component
@Service
public class MailContentBuilder {
	
	
	@Autowired
	private TemplateEngine templateEngine;
	
	
	public MailContentBuilder() {
		super();
	}

	public String build(Orders order) {
		
		Context context = new Context();
		
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("order", order);
		
		String pattern = "dd-MM-yyyy HH:mm";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String stringDate= simpleDateFormat.format(new Date());
		props.put("date", stringDate);

		
		context.setVariables(props);
		
		return templateEngine.process("MailTemplate", context);
	}

}
