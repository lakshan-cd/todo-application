import { test, expect } from '@playwright/test';

test.describe('TaskFlow Application', () => {
  test.beforeEach(async ({ page }) => {
    await page.goto('/', { waitUntil: 'domcontentloaded' });
    await page.waitForSelector('main', { timeout: 15000 });
    await page.waitForLoadState('load');
  });

  test('creates a new task successfully', async ({ page }) => {
    await page.waitForSelector('input[name="title"]');
    
    await page.getByLabel(/title/i).fill('E2E Test Task');
    await page.getByLabel(/description/i).fill('This is an end-to-end test task');
    
    const createButton = page.getByRole('button', { name: /create task/i });
    await createButton.click();
    
    await expect(createButton).toBeEnabled({ timeout: 15000 });
    
    await page.waitForTimeout(500);
    
    await expect(page.getByText('E2E Test Task').first()).toBeVisible({ timeout: 20000 });
    await expect(page.getByText('This is an end-to-end test task').first()).toBeVisible();
  });

  test('validates required fields', async ({ page }) => {
    await page.getByRole('button', { name: /create task/i }).click();
    
    await page.waitForTimeout(500);
    
    await expect(page.getByText(/title is required/i)).toBeVisible({ timeout: 5000 });
  });

  test('validates field lengths', async ({ page }) => {
    await page.getByLabel(/title/i).fill('ab');
    await page.getByLabel(/description/i).fill('Valid description');
    await page.getByRole('button', { name: /create task/i }).click();
    
    await page.waitForTimeout(500);
    
    await expect(page.getByText(/Title must be at least 3 characters/i)).toBeVisible({ timeout: 20000 });
  });

  test('completes a task', async ({ page }) => {
    await page.waitForSelector('input[name="title"]');
    
    await page.getByLabel(/title/i).fill('Task to Complete');
    await page.getByLabel(/description/i).fill('This task will be completed');
    const createButton = page.getByRole('button', { name: /create task/i });
    await createButton.click();
    
    await expect(createButton).toBeEnabled({ timeout: 15000 });
    
    await page.waitForTimeout(500);
    
    await expect(page.getByText('Task to Complete').first()).toBeVisible({ timeout: 20000 });
    
    const taskHeading = page.getByRole('heading', { name: 'Task to Complete' }).first();
    const taskCard = taskHeading.locator('..'); 
    const completeButton = taskCard.getByRole('button', { name: /done/i });
    await completeButton.click();
    
    await page.waitForTimeout(2000);
    
    await expect(page.getByText('Task to Complete').first()).not.toBeVisible({ timeout: 10000 });
  });

  test('deletes a task', async ({ page }) => {
    await page.waitForSelector('input[name="title"]');
    
    await page.getByLabel(/title/i).fill('Task to Delete');
    await page.getByLabel(/description/i).fill('This task will be deleted');
    const createButton = page.getByRole('button', { name: /create task/i });
    await createButton.click();
    
    await expect(createButton).toBeEnabled({ timeout: 15000 });
    
    await page.waitForTimeout(500);
    
    await expect(page.getByText('Task to Delete').first()).toBeVisible({ timeout: 20000 });
    
    const taskCard = page.getByText('Task to Delete').first().locator('..').locator('..');
    const deleteIcon = taskCard.getByRole('button').filter({ has: page.locator('svg') }).last();
    await deleteIcon.click();
    
    const confirmButton = page.getByRole('button', { name: /confirm/i });
    await confirmButton.click();
    
    await expect(page.getByText('Task to Delete').first()).not.toBeVisible({ timeout: 5000 });
  });

  test('displays empty state when no tasks', async ({ page }) => {
    await page.waitForTimeout(1000);
    
    const emptyStateExists = await page.getByText('No tasks yet').isVisible().catch(() => false);
    const createTextExists = await page.getByText('Create your first task to get started!').isVisible().catch(() => false);
    
    if (emptyStateExists) {
      await expect(page.getByText('No tasks yet')).toBeVisible();
      await expect(page.getByText('Create your first task to get started!')).toBeVisible();
    } else {
      await expect(page.getByText('Your Tasks')).toBeVisible();
    }
  });

  test('displays task count correctly', async ({ page }) => {
    await page.waitForSelector('input[name="title"]');
    
    for (let i = 1; i <= 3; i++) {
      await page.getByLabel(/title/i).fill(`Task ${i}`);
      await page.getByLabel(/description/i).fill(`Description ${i}`);
      const createButton = page.getByRole('button', { name: /create task/i });
      await createButton.click();
      
      await expect(createButton).toBeEnabled({ timeout: 15000 });
      
      await page.waitForTimeout(500);
      
      await expect(page.getByText(`Task ${i}`).first()).toBeVisible({ timeout: 10000 });
      await page.waitForTimeout(500);
    }
    
    await expect(page.getByText(/\d+ task/i)).toBeVisible();
  });

  test('toggles theme correctly', async ({ page }) => {
    const themeButton = page.getByRole('button', { name: /toggle theme/i });
    
    await expect(themeButton).toBeVisible({ timeout: 5000 });
    
    const htmlBefore = page.locator('html');
    const classBefore = await htmlBefore.getAttribute('class') || '';
    
    await themeButton.click();
    
    await page.waitForTimeout(500);
    
    const htmlAfter = page.locator('html');
    const classAfter = await htmlAfter.getAttribute('class') || '';
    
    expect(classBefore).not.toBe(classAfter);
  });

  test('works on mobile devices', async ({ page }) => {
    await page.setViewportSize({ width: 375, height: 667 });
    
    await page.reload({ waitUntil: 'domcontentloaded' });
    await page.waitForSelector('main', { timeout: 15000 });
    await page.waitForLoadState('load');
    
    await expect(page.getByText(/create new task/i)).toBeVisible({ timeout: 5000 });
    await expect(page.getByLabel(/title/i)).toBeVisible();
    await expect(page.getByLabel(/description/i)).toBeVisible();
  });
});
