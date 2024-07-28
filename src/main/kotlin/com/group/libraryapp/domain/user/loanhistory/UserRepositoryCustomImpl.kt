package com.group.libraryapp.domain.user.loanhistory

import com.group.libraryapp.domain.user.QUser.user
import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepositoryCustom
import com.group.libraryapp.domain.user.loanhistory.QUserLoanHistory.userLoanHistory
import com.querydsl.jpa.impl.JPAQueryFactory

class UserRepositoryCustomImpl(
    private val queryFactory: JPAQueryFactory,
) : UserRepositoryCustom {

    override fun findWithHistories(): List<User> =
        queryFactory
            .select(user)
            .distinct()
            .from(user)
            .leftJoin(userLoanHistory)
            .on(userLoanHistory.user.id.eq(user.id))
            .fetchJoin()
            .fetch()
}
