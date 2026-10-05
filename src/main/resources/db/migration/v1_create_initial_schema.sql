CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL,
    user_email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    role VARCHAR(30) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE customers (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    email VARCHAR(150),
    phone VARCHAR(20),
    address VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE sites (
    id BIGSERIAL PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    name VARCHAR(150) NOT NULL,
    address VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_site_customer
        FOREIGN KEY (customer_id)
        REFERENCES customers(id)
);

CREATE TABLE work_orders (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    priority VARCHAR(30) NOT NULL,
    status VARCHAR(30) NOT NULL,
    sla_due_date TIMESTAMP,

    customer_id BIGINT NOT NULL,
    site_id BIGINT NOT NULL,
    assignee_id BIGINT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_work_order_customer
        FOREIGN KEY (customer_id)
        REFERENCES customers(id),

    CONSTRAINT fk_work_order_site
        FOREIGN KEY (site_id)
        REFERENCES sites(id),

    CONSTRAINT fk_work_order_assignee
        FOREIGN KEY (assignee_id)
        REFERENCES users(id)
);

CREATE TABLE work_order_status_history (
    id BIGSERIAL PRIMARY KEY,
    work_order_id BIGINT NOT NULL,
    old_status VARCHAR(30),
    new_status VARCHAR(30) NOT NULL,
    changed_by BIGINT NOT NULL,
    changed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_status_history_work_order
        FOREIGN KEY (work_order_id)
        REFERENCES work_orders(id),

    CONSTRAINT fk_status_history_user
        FOREIGN KEY (changed_by)
        REFERENCES users(id)
);

CREATE TABLE parts (
    id BIGSERIAL PRIMARY KEY,
    part_number VARCHAR(100) NOT NULL UNIQUE,
    name VARCHAR(150) NOT NULL,
    description TEXT,
    stock_quantity INTEGER NOT NULL DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE part_usage (
    id BIGSERIAL PRIMARY KEY,
    work_order_id BIGINT NOT NULL,
    part_id BIGINT NOT NULL,
    quantity INTEGER NOT NULL,

    CONSTRAINT fk_part_usage_work_order
        FOREIGN KEY (work_order_id)
        REFERENCES work_orders(id),

    CONSTRAINT fk_part_usage_part
        FOREIGN KEY (part_id)
        REFERENCES parts(id)
);

CREATE TABLE time_logs (
    id BIGSERIAL PRIMARY KEY,
    work_order_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP,
    hours DECIMAL(5,2),
    description TEXT,

    CONSTRAINT fk_time_log_work_order
        FOREIGN KEY (work_order_id)
        REFERENCES work_orders(id),

    CONSTRAINT fk_time_log_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);