package com.awarepoint.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceptions thrown by controller due to entity not found; will be mapped to the appropriate http response.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="entity not found")
class NoSuchEntityIdException extends RuntimeException {
}
