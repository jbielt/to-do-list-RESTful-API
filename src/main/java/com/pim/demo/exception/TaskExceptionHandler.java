package com.pim.demo.exception;


import com.pim.demo.models.response.TaskErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TaskExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<TaskErrorResponse> handleException(TaskNotFoundException exc){

        //create a TaskErrorResponse
        TaskErrorResponse error = new TaskErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        //return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    //add another exception handler ... to catch any exception (catch all)
    @ExceptionHandler
    public ResponseEntity<TaskErrorResponse> handleException(Exception exc){

        //create a TaskErrorResponse
        TaskErrorResponse error = new TaskErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage()); //you can write any message here
        error.setTimeStamp(System.currentTimeMillis());

        //return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }



}
