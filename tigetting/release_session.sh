#!/bin/bash

# DB Connection Config
DB_HOST="127.0.0.1"
DB_PORT="3307"
DB_USER="ssafy"
DB_PASS="ssafy"
DB_NAME="tget"

echo "=== Force Expiring Active Tokens (Simulating User Exit) ==="

# 1. Update status to EXPIRED for all ACTIVE tokens in MySQL
mysql -h "$DB_HOST" -P "$DB_PORT" -u "$DB_USER" -p"$DB_PASS" "$DB_NAME" -e "UPDATE queue_tokens SET status = 'EXPIRED' WHERE status = 'ACTIVE';"
echo "✅ MySQL: Active tokens marked as EXPIRED."

# 2. Flush Redis to reset active token counters
# This ensures the backend knows spots are free
docker exec tget-redis redis-cli FLUSHALL
echo "✅ Redis: Cache flushed (Active token counts reset)."

echo "👉 The next person in the queue should enter IMMEDIATELY now."
