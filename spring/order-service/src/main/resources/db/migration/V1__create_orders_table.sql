CREATE TABLE order
(
    id         UUID PRIMARY KEY     DEFAULT gen_random_uuid(),
    product_id UUID        NOT NULL,
    quantity   INT         NOT NULL CHECK (quantity > 0),
    status     VACHAR(20) NOT NULL DEFAULT 'PENDING' CHECK (status IN ('PENDING', 'COMPLETED', 'CANCELLED', 'SHIPPED', 'REJECTED', 'RETURNED')),
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);