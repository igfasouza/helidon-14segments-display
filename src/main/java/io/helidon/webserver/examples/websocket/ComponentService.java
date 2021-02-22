package io.helidon.webserver.examples.websocket;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;
import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.Service;

/**
 * Class MessageQueueResource.
 */
public class ComponentService implements Service {

	GpioPinDigitalOutput pin01;
	GpioPinDigitalOutput pin02;
	GpioPinDigitalOutput pin04;
	GpioPinDigitalOutput pin05;
	GpioPinDigitalOutput pin06;
	GpioPinDigitalOutput pin07;
	GpioPinDigitalOutput pin08;
	GpioPinDigitalOutput pin09;
	GpioPinDigitalOutput pin10;
	GpioPinDigitalOutput pin11;
	GpioPinDigitalOutput pin12;
	GpioPinDigitalOutput pin13;
	GpioPinDigitalOutput pin14;
	GpioPinDigitalOutput pin15;
	GpioPinDigitalOutput pin16;
	GpioPinDigitalOutput pin17;
	GpioPinDigitalOutput pin18;
	
	public ComponentService() {
		
		final GpioController gpio = GpioFactory.getInstance();
		pin01 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29);
		pin02 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_10);
		pin04 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_09);
		pin05 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07);
		pin06 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01);
		pin07 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_26);
		pin08 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_13);
		pin09 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08);
		pin10 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27);
		pin12 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_15);
		pin13 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28);
		pin14 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05);
		pin15 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04);
		pin17 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02);
		pin18 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06);
		
		pin11 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_11);
		pin16 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25);
		
		pin01.low();
		pin02.low();
		pin04.low();
		pin05.low();
		pin06.low();
		pin07.low();
		pin08.low();
		pin09.low();
		pin10.low();
		pin12.low();
		pin13.low();
		pin14.low();
		pin15.low();
		pin17.low();
		pin18.low();
		
		pin11.low();
		pin16.high();
		
	}

	@Override
	public void update(Routing.Rules routingRules) {
		routingRules.post("/board", this::handlePost);
	}

	private void handlePost(ServerRequest request, ServerResponse response) {
		request.content()
		.as(String.class)
		.thenAccept(it -> {
			response.status(204).send();
			System.out.println("it " + it);
			String[] values = it.split(" ");
			led(values[0],values[1]);
		});
	}

	public void led(String led, String status) {

		if(led.equals("segment1")){
			if(status.equals("true")){
				pin01.high();
			}else {
				pin01.low();
			}
		}else if(led.equals("segment2")){
			if(status.equals("true")){
				pin02.high();
			}else {
				pin02.low();
			}
		}else if(led.equals("segment3")){
			if(status.equals("true")){
				pin04.high();
			}else {
				pin04.low();
			}
		}else if(led.equals("segment4")){
			if(status.equals("true")){
				pin05.high();
			}else {
				pin05.low();
			}
		}else if(led.equals("segment5")){
			if(status.equals("true")){
				pin06.high();
			}else {
				pin06.low();
			}
		}else if(led.equals("segment6")){
			if(status.equals("true")){
				pin07.high();
			}else {
				pin07.low();
			}
		}else if(led.equals("segment7")){
			if(status.equals("true")){
				pin08.high();
			}else {
				pin08.low();
			}
		}else if(led.equals("segment8")){
			if(status.equals("true")){
				pin09.high();
			}else {
				pin09.low();
			}
		}else if(led.equals("segment9")){
			if(status.equals("true")){
				pin10.high();
			}else {
				pin10.low();
			}
		}else if(led.equals("segment10")){
			if(status.equals("true")){
				pin12.high();
			}else {
				pin12.low();
			}
		}else if(led.equals("segment11")){
			if(status.equals("true")){
				pin13.high();
			}else {
				pin13.low();
			}
		}else if(led.equals("segment12")){
			if(status.equals("true")){
				pin14.high();
			}else {
				pin14.low();
			}
		}else if(led.equals("segment13")){
			if(status.equals("true")){
				pin15.high();
			}else {
				pin15.low();
			}
		}else if(led.equals("segment14")){
			if(status.equals("true")){
				pin17.high();
			}else {
				pin17.low();
			}
		}else if(led.equals("segmen15")){
			if(status.equals("true")){
				pin18.high();
			}else {
				pin18.low();
			}
		}

	}

}