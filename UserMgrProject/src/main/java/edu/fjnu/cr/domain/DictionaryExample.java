package edu.fjnu.cr.domain;

import java.util.ArrayList;
import java.util.List;

public class DictionaryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DictionaryExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andDictIdIsNull() {
            addCriterion("dict_id is null");
            return (Criteria) this;
        }

        public Criteria andDictIdIsNotNull() {
            addCriterion("dict_id is not null");
            return (Criteria) this;
        }

        public Criteria andDictIdEqualTo(Integer value) {
            addCriterion("dict_id =", value, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdNotEqualTo(Integer value) {
            addCriterion("dict_id <>", value, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdGreaterThan(Integer value) {
            addCriterion("dict_id >", value, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("dict_id >=", value, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdLessThan(Integer value) {
            addCriterion("dict_id <", value, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdLessThanOrEqualTo(Integer value) {
            addCriterion("dict_id <=", value, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdIn(List<Integer> values) {
            addCriterion("dict_id in", values, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdNotIn(List<Integer> values) {
            addCriterion("dict_id not in", values, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdBetween(Integer value1, Integer value2) {
            addCriterion("dict_id between", value1, value2, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdNotBetween(Integer value1, Integer value2) {
            addCriterion("dict_id not between", value1, value2, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictTypeIsNull() {
            addCriterion("dict_type is null");
            return (Criteria) this;
        }

        public Criteria andDictTypeIsNotNull() {
            addCriterion("dict_type is not null");
            return (Criteria) this;
        }

        public Criteria andDictTypeEqualTo(Integer value) {
            addCriterion("dict_type =", value, "dictType");
            return (Criteria) this;
        }

        public Criteria andDictTypeNotEqualTo(Integer value) {
            addCriterion("dict_type <>", value, "dictType");
            return (Criteria) this;
        }

        public Criteria andDictTypeGreaterThan(Integer value) {
            addCriterion("dict_type >", value, "dictType");
            return (Criteria) this;
        }

        public Criteria andDictTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("dict_type >=", value, "dictType");
            return (Criteria) this;
        }

        public Criteria andDictTypeLessThan(Integer value) {
            addCriterion("dict_type <", value, "dictType");
            return (Criteria) this;
        }

        public Criteria andDictTypeLessThanOrEqualTo(Integer value) {
            addCriterion("dict_type <=", value, "dictType");
            return (Criteria) this;
        }

        public Criteria andDictTypeIn(List<Integer> values) {
            addCriterion("dict_type in", values, "dictType");
            return (Criteria) this;
        }

        public Criteria andDictTypeNotIn(List<Integer> values) {
            addCriterion("dict_type not in", values, "dictType");
            return (Criteria) this;
        }

        public Criteria andDictTypeBetween(Integer value1, Integer value2) {
            addCriterion("dict_type between", value1, value2, "dictType");
            return (Criteria) this;
        }

        public Criteria andDictTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("dict_type not between", value1, value2, "dictType");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameIsNull() {
            addCriterion("dict_type_name is null");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameIsNotNull() {
            addCriterion("dict_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameEqualTo(String value) {
            addCriterion("dict_type_name =", value, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameNotEqualTo(String value) {
            addCriterion("dict_type_name <>", value, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameGreaterThan(String value) {
            addCriterion("dict_type_name >", value, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("dict_type_name >=", value, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameLessThan(String value) {
            addCriterion("dict_type_name <", value, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameLessThanOrEqualTo(String value) {
            addCriterion("dict_type_name <=", value, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameLike(String value) {
            addCriterion("dict_type_name like", value, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameNotLike(String value) {
            addCriterion("dict_type_name not like", value, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameIn(List<String> values) {
            addCriterion("dict_type_name in", values, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameNotIn(List<String> values) {
            addCriterion("dict_type_name not in", values, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameBetween(String value1, String value2) {
            addCriterion("dict_type_name between", value1, value2, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameNotBetween(String value1, String value2) {
            addCriterion("dict_type_name not between", value1, value2, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictNameIsNull() {
            addCriterion("dict_name is null");
            return (Criteria) this;
        }

        public Criteria andDictNameIsNotNull() {
            addCriterion("dict_name is not null");
            return (Criteria) this;
        }

        public Criteria andDictNameEqualTo(String value) {
            addCriterion("dict_name =", value, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameNotEqualTo(String value) {
            addCriterion("dict_name <>", value, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameGreaterThan(String value) {
            addCriterion("dict_name >", value, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameGreaterThanOrEqualTo(String value) {
            addCriterion("dict_name >=", value, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameLessThan(String value) {
            addCriterion("dict_name <", value, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameLessThanOrEqualTo(String value) {
            addCriterion("dict_name <=", value, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameLike(String value) {
            addCriterion("dict_name like", value, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameNotLike(String value) {
            addCriterion("dict_name not like", value, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameIn(List<String> values) {
            addCriterion("dict_name in", values, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameNotIn(List<String> values) {
            addCriterion("dict_name not in", values, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameBetween(String value1, String value2) {
            addCriterion("dict_name between", value1, value2, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameNotBetween(String value1, String value2) {
            addCriterion("dict_name not between", value1, value2, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictParentIsNull() {
            addCriterion("dict_parent is null");
            return (Criteria) this;
        }

        public Criteria andDictParentIsNotNull() {
            addCriterion("dict_parent is not null");
            return (Criteria) this;
        }

        public Criteria andDictParentEqualTo(Integer value) {
            addCriterion("dict_parent =", value, "dictParent");
            return (Criteria) this;
        }

        public Criteria andDictParentNotEqualTo(Integer value) {
            addCriterion("dict_parent <>", value, "dictParent");
            return (Criteria) this;
        }

        public Criteria andDictParentGreaterThan(Integer value) {
            addCriterion("dict_parent >", value, "dictParent");
            return (Criteria) this;
        }

        public Criteria andDictParentGreaterThanOrEqualTo(Integer value) {
            addCriterion("dict_parent >=", value, "dictParent");
            return (Criteria) this;
        }

        public Criteria andDictParentLessThan(Integer value) {
            addCriterion("dict_parent <", value, "dictParent");
            return (Criteria) this;
        }

        public Criteria andDictParentLessThanOrEqualTo(Integer value) {
            addCriterion("dict_parent <=", value, "dictParent");
            return (Criteria) this;
        }

        public Criteria andDictParentIn(List<Integer> values) {
            addCriterion("dict_parent in", values, "dictParent");
            return (Criteria) this;
        }

        public Criteria andDictParentNotIn(List<Integer> values) {
            addCriterion("dict_parent not in", values, "dictParent");
            return (Criteria) this;
        }

        public Criteria andDictParentBetween(Integer value1, Integer value2) {
            addCriterion("dict_parent between", value1, value2, "dictParent");
            return (Criteria) this;
        }

        public Criteria andDictParentNotBetween(Integer value1, Integer value2) {
            addCriterion("dict_parent not between", value1, value2, "dictParent");
            return (Criteria) this;
        }

        public Criteria andDictParentsIsNull() {
            addCriterion("dict_parents is null");
            return (Criteria) this;
        }

        public Criteria andDictParentsIsNotNull() {
            addCriterion("dict_parents is not null");
            return (Criteria) this;
        }

        public Criteria andDictParentsEqualTo(String value) {
            addCriterion("dict_parents =", value, "dictParents");
            return (Criteria) this;
        }

        public Criteria andDictParentsNotEqualTo(String value) {
            addCriterion("dict_parents <>", value, "dictParents");
            return (Criteria) this;
        }

        public Criteria andDictParentsGreaterThan(String value) {
            addCriterion("dict_parents >", value, "dictParents");
            return (Criteria) this;
        }

        public Criteria andDictParentsGreaterThanOrEqualTo(String value) {
            addCriterion("dict_parents >=", value, "dictParents");
            return (Criteria) this;
        }

        public Criteria andDictParentsLessThan(String value) {
            addCriterion("dict_parents <", value, "dictParents");
            return (Criteria) this;
        }

        public Criteria andDictParentsLessThanOrEqualTo(String value) {
            addCriterion("dict_parents <=", value, "dictParents");
            return (Criteria) this;
        }

        public Criteria andDictParentsLike(String value) {
            addCriterion("dict_parents like", value, "dictParents");
            return (Criteria) this;
        }

        public Criteria andDictParentsNotLike(String value) {
            addCriterion("dict_parents not like", value, "dictParents");
            return (Criteria) this;
        }

        public Criteria andDictParentsIn(List<String> values) {
            addCriterion("dict_parents in", values, "dictParents");
            return (Criteria) this;
        }

        public Criteria andDictParentsNotIn(List<String> values) {
            addCriterion("dict_parents not in", values, "dictParents");
            return (Criteria) this;
        }

        public Criteria andDictParentsBetween(String value1, String value2) {
            addCriterion("dict_parents between", value1, value2, "dictParents");
            return (Criteria) this;
        }

        public Criteria andDictParentsNotBetween(String value1, String value2) {
            addCriterion("dict_parents not between", value1, value2, "dictParents");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}