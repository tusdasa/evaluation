package net.tusdasa.evaluation.commons;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<Model extends Serializable> {

    public static final long CODE_SUCCESS = 200;
    public static final long CODE_ERROR = 500;
    public static final long CODE_VALIDATE_FAILED = 404;
    public static final long CODE_UNAUTHORIZED = 401;
    public static final long CODE_FORBIDDEN = 403;


    private long code;
    private String message;
    private String requestId;
    private Model data;
    private List<Model> table;

    public CommonResponse() {

    }

    public CommonResponse<Model> ok() {
        this.setCode(CommonResponse.CODE_SUCCESS);
        this.setRequestId(UUID.randomUUID().toString().replaceAll("-", ""));
        this.setMessage("success");
        return this;
    }

    public CommonResponse<Model> ok(String message) {
        this.setCode(CommonResponse.CODE_SUCCESS);
        this.setRequestId(UUID.randomUUID().toString().replaceAll("-", ""));
        this.setMessage(message);
        return this;
    }

    public CommonResponse<Model> error() {
        this.setCode(CommonResponse.CODE_ERROR);
        this.setRequestId(UUID.randomUUID().toString().replaceAll("-", ""));
        this.setMessage("failure");
        return this;
    }

    public CommonResponse<Model> error(String message) {
        this.setCode(CommonResponse.CODE_ERROR);
        this.setRequestId(UUID.randomUUID().toString().replaceAll("-", ""));
        this.setMessage(message);
        return this;
    }


    public CommonResponse<Model> data(Model data) {
        this.setData(data);
        return this;
    }

    public CommonResponse<Model> table(List<Model> table) {
        if (table == null) {
            table = new ArrayList<Model>();
        }
        this.setTable(table);
        return this;
    }

    public boolean success() {
        if (this.getCode() == CommonResponse.CODE_SUCCESS) {
            return true;
        }
        return false;
    }

    public Model getData() {
        return data;
    }

    public void setData(Model data) {
        this.data = data;
    }

    public List<Model> getTable() {
        return table;
    }

    public void setTable(List<Model> table) {
        this.table = table;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
