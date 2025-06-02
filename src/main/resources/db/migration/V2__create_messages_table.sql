CREATE TABLE messages (
    id BIGSERIAL PRIMARY KEY,
    content TEXT NOT NULL,
    sent_at TIMESTAMP NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_messages_user_id FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);