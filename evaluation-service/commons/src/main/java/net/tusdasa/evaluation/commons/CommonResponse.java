package net.tusdasa.evaluation.commons;

import com.fasterxml.jackson.annotation.JsonInclude;
import net.tusdasa.evaluation.utils.UUIDUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<Model extends Serializable> {

    public static final long CODE_SUCCESS = 200;
    public static final long CODE_ERROR = 500;
    public static final long CODE_VALIDATE_FAILED = 404;
    public static final long CODE_UNAUTHORIZED = 401;
    public static final long CODE_FORBIDDEN = 403;
    public static final long CODE_TIMEOUT = 408;


    private long code;
    private String message;
    private String requestId;
    private Model data;
    private List<Model> table;

    public CommonResponse() {

    }

    public CommonResponse<Model> ok() {
        this.setCode(CommonResponse.CODE_SUCCESS);
        this.setRequestId(UUIDUtils.UUID());
        this.setMessage("success");
        return this;
    }

    public CommonResponse<Model> ok(String message) {
        this.setCode(CommonResponse.CODE_SUCCESS);
        this.setRequestId(UUIDUtils.UUID());
        this.setMessage(message);
        return this;
    }

    public CommonResponse<Model> error() {
        this.setCode(CommonResponse.CODE_ERROR);
        this.setRequestId(UUIDUtils.UUID());
        this.setMessage("failure");
        return this;
    }

    public CommonResponse<Model> error(String message) {
        this.setCode(CommonResponse.CODE_ERROR);
        this.setRequestId(UUIDUtils.UUID());
        this.setMessage(message);
        return this;
    }

    public CommonResponse<Model> auth(String message) {
        this.setCode(CommonResponse.CODE_UNAUTHORIZED);
        this.setRequestId(UUIDUtils.UUID());
        this.setMessage(message);
        return this;
    }

    public CommonResponse<Model> bad() {
        this.setCode(CODE_FORBIDDEN);
        this.setRequestId(UUIDUtils.UUID());
        this.setMessage("禁止访问");
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

    public CommonResponse<Model> busy() {
        this.setCode(CODE_TIMEOUT);
        this.setRequestId(UUIDUtils.UUID());
        this.setMessage("已断路, 服务繁忙/离线, 请重试");
        return this;
    }

    public boolean success() {
        if (this.getCode() == CODE_TIMEOUT || this.getCode() == CODE_ERROR) {
            return false;
        }
        return true;
    }

    public boolean emptyTable() {
        if (this.getTable() != null && !this.getTable().isEmpty()) {
            return false;
        }
        return true;
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
