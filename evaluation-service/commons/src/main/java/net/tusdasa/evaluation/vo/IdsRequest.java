package net.tusdasa.evaluation.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.io.Serializable;
import java.util.*;

@Data
@With
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IdsRequest implements Serializable {

    private ArrayList<Integer> firstIds;

    private ArrayList<Integer> secondIds;

    public IdsRequest() {
        this.firstIds = new ArrayList<>();
        this.secondIds = new ArrayList<>();
    }

    private Integer[] DuplicateArray(Integer[] array) {

        Set<Integer> set = new LinkedHashSet<>();
        for (int i = 0; i < array.length; i++) {
            set.add(array[i]);
        }
        Integer[] newArray = new Integer[set.size()];

        int i = 0;
        for (Integer value : set) {
            newArray[i] = value;
            i++;
        }

        return newArray;
    }

    public Integer[] getFirstArray() {
        return this.getIntegerIteratorArray(this.getFirstIds());
    }

    private Integer[] getArray(List<Integer> ids) {
        return new Integer[ids.size()];
    }

    private Integer[] getIntegerIteratorArray(List<Integer> ids) {
        Integer[] id = this.getArray(ids);
        Iterator<Integer> iterator = ids.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            id[i] = iterator.next();
            i++;
        }
        this.DuplicateArray(id);
        Arrays.sort(id);
        return id;
    }

    public Integer[] getSecondIdsArray() {
        return this.getIntegerIteratorArray(this.getSecondIds());
    }


    public IdsRequest addFirstIds(Integer ids) {
        if (this.getFirstIds() == null) this.setFirstIds(new ArrayList<>());
        this.firstIds.add(ids);
        return this;
    }

    public void addFirstId(Integer ids) {
        if (this.getFirstIds() == null) this.setFirstIds(new ArrayList<>());
        this.firstIds.add(ids);
    }

    public IdsRequest addSecondIds(Integer ids) {
        if (this.getSecondIds() == null) this.setSecondIds(new ArrayList<>());
        this.secondIds.add(ids);
        return this;
    }

    public void addSecondId(Integer ids) {
        if (this.getSecondIds() == null) this.setSecondIds(new ArrayList<>());
        this.secondIds.add(ids);
    }
}
