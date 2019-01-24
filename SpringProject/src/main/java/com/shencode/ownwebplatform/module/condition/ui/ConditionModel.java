package com.shencode.ownwebplatform.module.condition.ui;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shencode.ownwebplatform.module.condition.util.JsonUtil;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

public class ConditionModel {
    private static final String separator = "-";

    private Integer page = 0;
    private Integer size = 15;
    private Map<String, String> map = null;

    public Map<String, String> getSort() {
        return sort;
    }

    public void setSort(Map<String, String> sort) {
        this.sort = sort;
    }

    private Map<String, String> sort=null;

    @JsonIgnore
    private Map<String, String> hashAlias = new HashMap();
    @JsonIgnore
    private Map<String, Collection<ConditionExpression>> hashCondition = new HashMap();
    @JsonIgnore
    private Map<String, OrderBy> hashOrderBy = new LinkedHashMap();

    public ConditionModel() {
    }

    public ConditionModel(Map<String, String> jsonMap) {
        this.setMap(jsonMap);
        this.generate();
    }

    public ConditionModel(Map<String, String> jsonMap, Integer page, Integer size) {
        this.setMap(jsonMap);
        this.setPage(page);
        this.setSize(size);
        this.generate();
    }

    public ConditionModel(String json) {
        if (!JsonUtil.isBlank(json)) {
            this.setMap((Map)JsonUtil.decode(json, Map.class, String.class, String.class));
            this.generate();
        }
    }

    public ConditionModel(String json, Integer page, Integer size) {
        this.setPage(page);
        this.setSize(size);
        this.size = size == null ? 15 : size;
        this.setMap((Map)JsonUtil.decode(json, Map.class, String.class, String.class));
        this.generate();
    }

    public ConditionModel(Integer page, Integer size) {
        this.setPage(page);
        this.setSize(size);
    }

    private void generate() {

        //解析并生成查询条件
        Map<String, String> map = this.getMap();
        if (!CollectionUtils.isEmpty(map)) {
            String column = null;
            String op = null;
            String v = null;
            String matchMode = "anyMatch";

            for(Iterator i$ = map.keySet().iterator(); i$.hasNext(); this.addCondition(column, op, v, matchMode)) {
                String key = (String)i$.next();
                v = (String)map.get(key);
                if (key.endsWith("eq")) {
                    column = key.substring(0, key.lastIndexOf("-"));
                    op = "eq";
                } else if (key.endsWith("nq")) {
                    column = key.substring(0, key.lastIndexOf("-"));
                    op = "nq";
                } else if (key.endsWith("gt")) {
                    column = key.substring(0, key.lastIndexOf("-"));
                    op = "gt";
                } else if (key.endsWith("ge")) {
                    column = key.substring(0, key.lastIndexOf("-"));
                    op = "ge";
                } else if (key.endsWith("lt")) {
                    column = key.substring(0, key.lastIndexOf("-"));
                    op = "lt";
                } else if (key.endsWith("le")) {
                    column = key.substring(0, key.lastIndexOf("-"));
                    op = "le";
                } else if (key.endsWith("lk")) {
                    column = key.substring(0, key.lastIndexOf("-"));
                    op = "lk";
                } else if (key.endsWith("lks")) {
                    column = key.substring(0, key.lastIndexOf("-"));
                    op = "lk";
                    matchMode = "startMatch";
                } else if (key.endsWith("lke")) {
                    column = key.substring(0, key.lastIndexOf("-"));
                    op = "lk";
                    matchMode = "endMatch";
                } else if (key.endsWith("in")) {
                    column = key.substring(0, key.lastIndexOf("-"));
                    op = "in";
                } else if (key.endsWith("nul")) {
                    column = key.substring(0, key.lastIndexOf("-"));
                    op = "nul";
                } else if (key.endsWith("nnl")) {
                    column = key.substring(0, key.lastIndexOf("-"));
                    op = "nnl";
                } else {
                    column = key;
                    op = "lk";
                    matchMode = "anyMatch";
                }
            }
        }

        //解析并生成排序规则
        Map<String, String> sortMap = this.getSort();
        if (!CollectionUtils.isEmpty(sortMap)) {
            for(Iterator i$ = sortMap.keySet().iterator(); i$.hasNext();) {
                String key = (String)i$.next();
                String v = sortMap.get(key).toLowerCase();
                //考虑前端asc,desc没写或者写错的情况。
                if(v.equals("desc")){//倒叙
                    addOrder(key,v);
                }
                else{
                    addOrder(key,"asc");//正序
                }
            }
        }
    }

    public void addAlias(String column) {
        if (StringUtils.hasText(column) && this.hashAlias.get(column) == null) {
            if (column.indexOf(".") != -1) {
                String alias = column.substring(0, column.lastIndexOf("."));
                int pos = alias.indexOf(".");
                if (pos > -1) {
                    String[] aliases = alias.split("\\.");
                    String lastAlias = null;

                    for(int i = 0; i < aliases.length; ++i) {
                        String a = aliases[i];
                        if (lastAlias != null) {
                            a = lastAlias + "." + a;
                        }

                        if (!this.hashAlias.containsKey(a)) {
                            this.hashAlias.put(a, a.replaceAll("\\.", "_"));
                        }

                        lastAlias = a;
                    }
                } else if (!this.hashAlias.containsKey(alias)) {
                    this.hashAlias.put(alias, alias.replaceAll("\\.", "_"));
                }

            }
        }
    }

    public void removeAlias(String column) {
        if (StringUtils.hasText(column)) {
            if (column.indexOf(".") != -1) {
                String alias = column.substring(0, column.lastIndexOf("."));
                int pos = alias.indexOf(".");
                if (pos > -1) {
                    String[] aliases = alias.split("\\.");
                    String lastAlias = null;

                    for(int i = 0; i < aliases.length; ++i) {
                        String a = aliases[i];
                        if (lastAlias != null) {
                            a = lastAlias + "." + a;
                        }

                        if (this.hashAlias.containsKey(a)) {
                            this.hashAlias.remove(a);
                        }

                        lastAlias = a;
                    }
                } else if (this.hashAlias.containsKey(alias)) {
                    this.hashAlias.remove(alias);
                }

            }
        }
    }

    public int getAliasSize() {
        return this.hashAlias.size();
    }

    public boolean containsAlias(String alias) {
        return this.hashAlias.containsKey(alias);
    }

    public String getAliasValue(String alias) {
        String value = (String)this.hashAlias.get(alias);
        if (!StringUtils.hasText(value)) {
            int pos = alias.lastIndexOf(".");
            if (pos == -1) {
                value = alias;
            } else {
                value = alias.substring(0, pos).replaceAll("\\.", "_") + alias.substring(pos);
            }
        }

        return value;
    }

    public Set<String> getAliasKey() {
        return this.hashAlias.keySet();
    }

    public Collection<String> getAliasValues() {
        return this.hashAlias.values();
    }

    public ConditionExpression addCondition(String column, String op, Object value) {
        return this.addCondition(column, op, value, "anyMatch");
    }

    public ConditionExpression addCondition(String column, String op, Object value, String matchMode) {
        this.addAlias(column);
        ConditionExpression condition = new ConditionExpression(column, op, value, matchMode);
        Collection<ConditionExpression> conditions = (Collection)this.hashCondition.get(column);
        if (conditions == null) {
            conditions = new ArrayList();
        }

        ((Collection)conditions).add(condition);
        this.hashCondition.put(column, conditions);
        return condition;
    }

    public void removeCondition(String column) {
        this.removeAlias(column);
        this.hashCondition.remove(column);
    }

    public int getConditionSize() {
        return this.hashCondition.size();
    }

    public ConditionExpression getCondition(String column) {
        Collection<ConditionExpression> conditions = this.getConditions(column);
        return conditions != null && conditions.size() > 0 ? (ConditionExpression)conditions.iterator().next() : null;
    }

    public Collection<ConditionExpression> getConditions(String column) {
        return StringUtils.hasText(column) && this.hashCondition != null && this.hashCondition.size() != 0 ? (Collection)this.hashCondition.get(column) : null;
    }

    public Set<String> getColumns() {
        if(map.size()>0&&hashCondition.size()==0){
            generate();
        }
        return this.hashCondition.keySet();
    }

    public void addOrder(String column, String direction) {
        if (StringUtils.hasText(column)) {
            this.addAlias(column);
            this.getHashOrderBy().put(column, new OrderBy(column, direction));
        }

    }

    public boolean hasOrderByColumn(String column) {
        return this.getHashOrderBy().containsKey(column);
    }

    public OrderBy getOrderBy(String column) {
        return (OrderBy) this.getHashOrderBy().get(column);
    }

    public Collection<OrderBy> getOrderBys() {
        return this.getHashOrderBy().values();
    }

    public void clearOrderBy() {
        this.getHashOrderBy().clear();
    }

    public int getOrderByCount() {
        return this.getHashOrderBy().size();
    }

    public String toOrderByString() {
        String result = "";

        OrderBy orderBy;
        for(Iterator i$ = this.getHashOrderBy().values().iterator(); i$.hasNext(); result = "," + orderBy.toString()) {
            orderBy = (OrderBy)i$.next();
        }

        if (StringUtils.hasText(result)) {
            result = result.substring(1);
        }

        return result;
    }

    public String toOrderBySqlString() {
        String result = "";

        OrderBy orderBy;
        for(Iterator i$ = this.getHashOrderBy().values().iterator(); i$.hasNext(); result = "," + orderBy.toSqlString()) {
            orderBy = (OrderBy)i$.next();
        }

        if (StringUtils.hasText(result)) {
            result = result.substring(1);
        }

        return result;
    }

    public <T> Object getValue(String column, Class<T> valueClazz) {
        ConditionExpression condition = this.getCondition(column);
        return condition == null ? null : condition.getValue(valueClazz);
    }

    public String toString() {
        if(map.size()>0&&hashCondition.size()==0){
            generate();
        }
        StringBuffer sb = new StringBuffer();
        Iterator i$ = this.getColumns().iterator();

        while(i$.hasNext()) {
            String column = (String)i$.next();
            ConditionExpression ce = this.getCondition(column);
            if (ce != null) {
                sb.append(", ").append(column).append(" ").append(ce.getOp()).append(" ").append(ce.getValue());
            }
        }
        if(sb.length()>0){
            return sb.substring(1);
        }
        else{
            return toJsonString();
        }
    }

    public String toJsonString() {
        return "MyCondition{" +
                "jsonMap=" + map+
                ", hashAlias=" + hashAlias +
                ", hashCondition=" + hashCondition +
                ", hashOrderBy=" + hashOrderBy +
                ", start=" + page +
                ", limit=" + size +
                '}';
    }

    public int getPage() {
        return this.page;
    }

    public int getSize() {
        return this.size;
    }

    public void setPage(Integer page) {
        this.page = page == null ? 0 : page;
    }

    public void setSize(Integer size) {
        this.size = size == null ? 15 : size;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public Map<String, OrderBy> getHashOrderBy() {
        return hashOrderBy;
    }

    public void setHashOrderBy(Map<String, OrderBy> hashOrderBy) {
        this.hashOrderBy = hashOrderBy;
    }
}
