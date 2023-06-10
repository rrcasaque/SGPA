package com.example.sgpa.domain.usecases.utils.validation;

import java.time.LocalDateTime;

public class VerifyDateUseCase {
	
	public static void verify(LocalDateTime start, LocalDateTime end) throws Exception{
		if(start == null || end == null)
			throw new IllegalArgumentException("Initial and final date must be informed");
		if(start.isAfter(LocalDateTime.now()))
			throw new IllegalArgumentException("Invalid Start Date!");
		if(end.isBefore(start))
			throw new IllegalArgumentException("Invalid end Date!");
	}
}
