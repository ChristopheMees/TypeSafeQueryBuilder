/*
 * Copyright Gert Wijns gert.wijns@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package be.shad.tsqb.query;

import java.util.Collection;
import java.util.Date;

import be.shad.tsqb.ordering.OnGoingOrderBy;
import be.shad.tsqb.restrictions.OnGoingBooleanRestriction;
import be.shad.tsqb.restrictions.OnGoingDateRestriction;
import be.shad.tsqb.restrictions.OnGoingEnumRestriction;
import be.shad.tsqb.restrictions.OnGoingNumberRestriction;
import be.shad.tsqb.restrictions.OnGoingObjectRestriction;
import be.shad.tsqb.restrictions.OnGoingTextRestriction;
import be.shad.tsqb.restrictions.Restriction;
import be.shad.tsqb.restrictions.RestrictionChainable;
import be.shad.tsqb.restrictions.RestrictionHolder;
import be.shad.tsqb.restrictions.RestrictionsGroup;
import be.shad.tsqb.restrictions.RestrictionsGroupFactory;
import be.shad.tsqb.restrictions.WhereRestrictions;
import be.shad.tsqb.restrictions.predicate.RestrictionPredicate;
import be.shad.tsqb.values.CaseTypeSafeValue;
import be.shad.tsqb.values.CustomTypeSafeValue;
import be.shad.tsqb.values.HqlQueryValue;
import be.shad.tsqb.values.TypeSafeValue;
import be.shad.tsqb.values.TypeSafeValueFunctions;
import be.shad.tsqb.values.arithmetic.ArithmeticTypeSafeValueFactory;

/**
 * @author Gert
 *
 */
public abstract class TypeSafeQueryBuilder implements TypeSafeQueryFunction {
    private RestrictionsGroupFactory rb;
    private TypeSafeQuery query;

    @Override
    public void apply(TypeSafeQuery query) {
        this.query = query;
        this.rb = query.getGroupedRestrictionsBuilder();
        doto(query);
    }

    protected abstract void doto(TypeSafeQuery query);

    public RestrictionsGroup anyOf(RestrictionHolder restriction, RestrictionHolder... restrictions) {
        return rb.or(restriction, restrictions);
    }

    public RestrictionsGroup createRestrictionsGroup() {
        return rb.createRestrictionsGroup();
    }

    public RestrictionsGroup allOf(RestrictionHolder restriction, RestrictionHolder... restrictions) {
        return rb.and(restriction, restrictions);
    }

    public RestrictionChainable is() {
        return rb.where();
    }

    public RestrictionChainable is(HqlQueryValue hqlQueryvalue) {
        return rb.where(hqlQueryvalue);
    }

    public RestrictionChainable is(RestrictionsGroup group) {
        return rb.where(group);
    }

    public RestrictionChainable is(Restriction restriction) {
        return rb.where(restriction);
    }

    public <E extends Enum<E>> OnGoingEnumRestriction<E> is(E value) {
        return rb.where(value);
    }

    public OnGoingBooleanRestriction is(Boolean value) {
        return rb.where(value);
    }

    public OnGoingNumberRestriction is(Number value) {
        return rb.where(value);
    }

    public OnGoingDateRestriction is(Date value) {
        return rb.where(value);
    }

    public OnGoingTextRestriction is(String value) {
        return rb.where(value);
    }

    public <T> OnGoingObjectRestriction<T> is(TypeSafeValue<T> value) {
        return rb.where(value);
    }

    public RestrictionChainable having() {
        return query.having();
    }

    public RestrictionChainable having(HqlQueryValue restriction) {
        return query.having(restriction);
    }

    public RestrictionChainable having(RestrictionsGroup group) {
        return query.having(group);
    }

    public RestrictionChainable having(Restriction restriction) {
        return query.having(restriction);
    }

    public <E extends Enum<E>> OnGoingEnumRestriction<E> havingEnum(TypeSafeValue<E> value) {
        return query.havingEnum(value);
    }

    public <E extends Enum<E>> OnGoingEnumRestriction<E> having(E value) {
        return query.having(value);
    }

    public OnGoingBooleanRestriction havingBoolean(TypeSafeValue<Boolean> value) {
        return query.havingBoolean(value);
    }

    public OnGoingBooleanRestriction having(Boolean value) {
        return query.having(value);
    }

    public <N extends Number> OnGoingNumberRestriction havingNumber(TypeSafeValue<N> value) {
        return query.havingNumber(value);
    }

    public <T> T from(Class<T> fromClass) {
        return query.from(fromClass);
    }

    public <T> T from(Class<T> fromClass, String name) {
        return query.from(fromClass, name);
    }

    public OnGoingNumberRestriction having(Number value) {
        return query.having(value);
    }

    public OnGoingDateRestriction havingDate(TypeSafeValue<Date> value) {
        return query.havingDate(value);
    }

    public OnGoingDateRestriction having(Date value) {
        return query.having(value);
    }

    public <S, T extends S> T getAsSubtype(S proxy, Class<T> subtype) throws IllegalArgumentException {
        return query.getAsSubtype(proxy, subtype);
    }

    public OnGoingTextRestriction havingString(TypeSafeValue<String> value) {
        return query.havingString(value);
    }

    public OnGoingTextRestriction having(String value) {
        return query.having(value);
    }

    public TypeSafeQueryJoin join(JoinType joinType) {
        return query.join(joinType);
    }

    public RestrictionChainable havingExists(TypeSafeSubQuery<?> subquery) {
        return query.havingExists(subquery);
    }

    public RestrictionChainable havingNotExists(TypeSafeSubQuery<?> subquery) {
        return query.havingNotExists(subquery);
    }

    public <T> T join(Collection<T> anyCollection) {
        return query.join(anyCollection);
    }

    public <T> T join(Collection<T> anyCollection, String name) {
        return query.join(anyCollection, name);
    }

    public <T> T join(T anyObject) {
        return query.join(anyObject);
    }

    public <T> T join(T anyObject, String name) {
        return query.join(anyObject, name);
    }

    public <T> T join(Collection<T> anyCollection, JoinType joinType) {
        return query.join(anyCollection, joinType);
    }

    public <T> T join(Collection<T> anyCollection, JoinType joinType, String name) {
        return query.join(anyCollection, joinType, name);
    }

    public <T> T join(Collection<T> anyCollection, JoinType joinType, boolean createAdditionalJoin) {
        return query.join(anyCollection, joinType, createAdditionalJoin);
    }

    public <T> T join(Collection<T> anyCollection, JoinType joinType, String name, boolean createAdditionalJoin) {
        return query.join(anyCollection, joinType, name, createAdditionalJoin);
    }

    public <T> T join(T anyObject, JoinType joinType) {
        return query.join(anyObject, joinType);
    }

    public <T> T join(T anyObject, JoinType joinType, String name) {
        return query.join(anyObject, joinType, name);
    }

    public <T> T join(T anyObject, JoinType joinType, boolean createAdditionalJoin) {
        return query.join(anyObject, joinType, createAdditionalJoin);
    }

    public <T> T join(T anyObject, JoinType joinType, String name, boolean createAdditionalJoin) {
        return query.join(anyObject, joinType, name, createAdditionalJoin);
    }

    public <T> WhereRestrictions joinWith(T obj) {
        return query.joinWith(obj);
    }

    public OnGoingOrderBy orderBy() {
        return query.orderBy();
    }

    public <N extends Number> TypeSafeValue<N> groupBy(N val) {
        return query.groupBy(val);
    }

    public TypeSafeValue<String> groupBy(String val) {
        return query.groupBy(val);
    }

    public <E extends Enum<E>> TypeSafeValue<E> groupBy(E val) {
        return query.groupBy(val);
    }

    public TypeSafeValue<Boolean> groupBy(Boolean val) {
        return query.groupBy(val);
    }

    public TypeSafeValue<Date> groupBy(Date val) {
        return query.groupBy(val);
    }

    public <T> TypeSafeValue<T> groupBy(TypeSafeValue<T> val) {
        return query.groupBy(val);
    }

    public <T> TypeSafeSubQuery<T> subquery(Class<T> resultClass) {
        return query.subquery(resultClass);
    }

    public TypeSafeValueFunctions hqlFunction() {
        return query.hqlFunction();
    }

    public ArithmeticTypeSafeValueFactory getArithmeticsBuilder() {
        return query.getArithmeticsBuilder();
    }

    public RestrictionsGroupFactory getGroupedRestrictionsBuilder() {
        return query.getGroupedRestrictionsBuilder();
    }

    public void apply(TypeSafeQueryFunction fn) {
        query.apply(fn);
    }

    public TypeSafeNameds named() {
        return query.named();
    }

    public <VAL> CustomTypeSafeValue<VAL> customValue(Class<VAL> valueClass, String hql, Object... params) {
        return query.customValue(valueClass, hql, params);
    }

    public <VAL> CaseTypeSafeValue<VAL> caseWhenValue(Class<VAL> valueClass) {
        return query.caseWhenValue(valueClass);
    }

    public <VAL> TypeSafeValue<VAL> toValue(VAL val) {
        return query.toValue(val);
    }

    public void setHqlAlias(Object value, String alias) {
        query.setHqlAlias(value, alias);
    }

    public <T> T getByHqlAlias(String alias) {
        return query.getByHqlAlias(alias);
    }

    public RestrictionPredicate getDefaultRestrictionPredicate() {
        return query.getDefaultRestrictionPredicate();
    }

    public void setDefaultRestrictionPredicate(RestrictionPredicate restrictionValuePredicate) {
        query.setDefaultRestrictionPredicate(restrictionValuePredicate);
    }

}
