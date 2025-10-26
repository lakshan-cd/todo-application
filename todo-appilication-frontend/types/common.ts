export type ErrorData = {
    error: string;
    exceptionCode: string;
    status: string;
  };
  
  export type ErrorResponse = {
    data: ErrorData;
  };
  
  export type ErrorType = {
    response: ErrorResponse;
  };