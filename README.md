---
title: 티켓 예매 시스템 유스케이스 다이어그램
---
left to right direction
actor "사용자 (User)" as User
actor "관리자 (Admin)" as Admin

rectangle "티켓 예매 시스템" {
  User -- (회원 관리)
  User -- (공연 정보 조회)
  User -- (좌석 선택)
  User -- (공연 예매)
  User -- (예매 내역 관리)

  (공연 예매) ..> (좌석 선택) : <<include>>
  (예매 내역 관리) ..> (예매 취소) : <<extend>>
  (좌석 선택) ..> (좌석 잠금 연장) : <<extend>>


  Admin -- (관리자 기능)
  (관리자 기능) ..> (관리자 계정 관리) : <<include>>
  (관리자 기능) ..> (시스템 대시보드 조회) : <<include>>
  (관리자 계정 관리) --|> (회원 관리)

}

rectangle "회원 관리" {
  usecase "로그인" as UC_LOGIN
  usecase "로그아웃" as UC_LOGOUT
}
(회원 관리) -- UC_LOGIN
(회원 관리) -- UC_LOGOUT


rectangle "공연 정보 조회" {
  usecase "공연 목록 조회" as UC_LIST_PERF
  usecase "공연 상세 조회" as UC_DETAIL_PERF
  usecase "공연 검색" as UC_SEARCH_PERF
}
(공연 정보 조회) -- UC_LIST_PERF
(공연 정보 조회) -- UC_DETAIL_PERF
(공연 정보 조회) -- UC_SEARCH_PERF


rectangle "좌석 선택" {
  usecase "회차별 좌석 현황 조회" as UC_LIST_SEATS
  usecase "좌석 잠금" as UC_LOCK_SEATS
  usecase "좌석 잠금 해제" as UC_UNLOCK_SEATS
}
(좌석 선택) -- UC_LIST_SEATS
(좌석 선택) -- UC_LOCK_SEATS
(좌석 선택) -- UC_UNLOCK_SEATS


rectangle "공연 예매" {
  usecase "공연 예매하기" as UC_CREATE_BOOKING
}
(공연 예매) -- UC_CREATE_BOOKING


rectangle "예매 내역 관리" {
  usecase "예매 목록 조회" as UC_LIST_BOOKINGS
  usecase "예매 상세 조회" as UC_DETAIL_BOOKING
}
(예매 내역 관리) -- UC_LIST_BOOKINGS
(예매 내역 관리) -- UC_DETAIL_BOOKING


rectangle "관리자 기능" {
    usecase "관리자 로그인/로그아웃"
}
(관리자 기능) -- (관리자 로그인/로그아웃)


rectangle "관리자 계정 관리" {
  usecase "관리자 계정 생성" as UC_CREATE_ADMIN
  usecase "관리자 목록 조회" as UC_LIST_ADMIN
  usecase "관리자 계정 삭제" as UC_DELETE_ADMIN
}
(관리자 계정 관리) -- UC_CREATE_ADMIN
(관리자 계정 관리) -- UC_LIST_ADMIN
(관리자 계정 관리) -- UC_DELETE_ADMIN


rectangle "시스템 대시보드 조회" {
    usecase "시스템 상태 조회" as UC_SYSTEM_STATUS
}
(시스템 대시보드 조회) -- UC_SYSTEM_STATUS
