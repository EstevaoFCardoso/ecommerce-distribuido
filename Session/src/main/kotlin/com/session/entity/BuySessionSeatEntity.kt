package com.session.entity

import jakarta.persistence.*
import org.springframework.stereotype.Component

class BuySessionSeatEntity (

    @Id
    @Column(name = "ID_SESSION")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "SEAT_ID")
    val seat: SeatEntity,

    @ManyToOne
    @JoinColumn(name = "SESSION_ID")
    val session: SessionEntity,

    @ManyToOne
    @JoinColumn(name = "SESSION_ID")
    val ticket: TicketEntity,

    @ManyToOne
    @JoinColumn(name = "BUY_ID")
    val buy: BuyEntity,

    @ManyToOne
    @JoinColumn(name = "PRICE_PAID")
    val pricePaid: Double
)

@Component
class BuySessionSeatAssembler(
    private val seatAssembler: SeatAssembler,
    private val sessionAssembler: SessionAssembler,
    private val ticketAssembler: TicketAssembler,
    private val buyAssembler: BuyAssembler
) {
    fun toDTO(entity: BuySessionSeatEntity): BuySessionSeatDTO {
        return BuySessionSeatDTO(
            seat = seatAssembler.toDTO(entity.seat),
            session = sessionAssembler.toDTO(entity.session),
            ticket = ticketAssembler.toDTO(entity.ticket),
            buy = buyAssembler.toDTO(entity.buy),
            pricePaid = entity.pricePaid
        )
    }

    fun toEntity(dto: BuySessionSeatDTO): BuySessionSeatEntity {
        return BuySessionSeatEntity(
            seat = seatAssembler.toEntity(dto.seat),
            session = sessionAssembler.toEntity(dto.session),
            ticket = ticketAssembler.toEntity(dto.ticket),
            buy = buyAssembler.toEntity(dto.buy),
            pricePaid = dto.pricePaid
        )
    }
}