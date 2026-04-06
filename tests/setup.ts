import { execSync } from 'child_process';
import path from 'path';

const testDbPath = path.resolve(__dirname, '../prisma/test.db');
const testDbUrl = `file:${testDbPath}`;
process.env.DATABASE_URL = testDbUrl;
process.env.JWT_SECRET = 'test-secret';

execSync('npx prisma migrate deploy', {
  stdio: 'inherit',
  env: { ...process.env, DATABASE_URL: testDbUrl },
});

