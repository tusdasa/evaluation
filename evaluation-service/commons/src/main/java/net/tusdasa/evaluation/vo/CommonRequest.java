package net.tusdasa.evaluation.vo;

public interface CommonRequest<Model> {

    Model build();

    boolean isUpdateRequest();

    boolean isCreateRequest();
}
