package com.example.sgpa.domain.usecases.utils.validation;

import java.time.LocalDateTime;

public class VerifyDateUseCase {
	
	public static void verify(DateType dateType, LocalDateTime date) {
		switch (dateType) {
			case START:
				if(date.isAfter(LocalDateTime.now()))
					throw new RuntimeException("Invalid Start Date!");
		//	case END:
		//		if(date.isBefore(LocalDateTime.now()))
		//			throw new RuntimeException("Invalid End Date!");

		}
	}
}
