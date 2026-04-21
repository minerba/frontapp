// netlify/functions/credit-history.js
// GET    /api/credit-history?agency=KCB   → 히스토리 조회
// POST   /api/credit-history              → 히스토리 추가
// PUT    /api/credit-history?id=1         → 히스토리 수정
// DELETE /api/credit-history?id=1         → 히스토리 삭제

const { createClient } = require('@supabase/supabase-js');

const supabase = createClient(
  process.env.SUPABASE_URL,
  process.env.SUPABASE_SERVICE_KEY
);

exports.handler = async (event) => {
  const headers = {
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
    'Access-Control-Allow-Headers': 'Content-Type',
  };

  if (event.httpMethod === 'OPTIONS') return { statusCode: 204, headers, body: '' };

  const params = event.queryStringParameters || {};

  if (event.httpMethod === 'GET') {
    const { agency } = params;
    if (!agency) return { statusCode: 400, headers, body: JSON.stringify({ error: 'agency 파라미터가 필요합니다' }) };

    const { data, error } = await supabase
      .from('credit_history')
      .select('*')
      .eq('agency', agency)
      .order('year', { ascending: false })
      .order('created_at', { ascending: false });

    if (error) return { statusCode: 500, headers, body: JSON.stringify({ error: error.message }) };
    return { statusCode: 200, headers, body: JSON.stringify(data) };
  }

  if (event.httpMethod === 'POST') {
    const body = JSON.parse(event.body || '{}');
    const { agency, type, year, date, title, description, has_card, change_amount, score_from, score_to } = body;

    if (!agency || !type || !year || !date) {
      return { statusCode: 400, headers, body: JSON.stringify({ error: 'agency, type, year, date는 필수입니다' }) };
    }

    const { data, error } = await supabase
      .from('credit_history')
      .insert({ agency, type, year, date, title, description, has_card, change_amount, score_from, score_to })
      .select()
      .single();

    if (error) return { statusCode: 500, headers, body: JSON.stringify({ error: error.message }) };
    return { statusCode: 201, headers, body: JSON.stringify(data) };
  }

  if (event.httpMethod === 'PUT') {
    const { id } = params;
    if (!id) return { statusCode: 400, headers, body: JSON.stringify({ error: 'id 파라미터가 필요합니다' }) };

    const body = JSON.parse(event.body || '{}');
    const { data, error } = await supabase
      .from('credit_history')
      .update(body)
      .eq('id', id)
      .select()
      .single();

    if (error) return { statusCode: 500, headers, body: JSON.stringify({ error: error.message }) };
    return { statusCode: 200, headers, body: JSON.stringify(data) };
  }

  if (event.httpMethod === 'DELETE') {
    const { id } = params;
    if (!id) return { statusCode: 400, headers, body: JSON.stringify({ error: 'id 파라미터가 필요합니다' }) };

    const { error } = await supabase.from('credit_history').delete().eq('id', id);
    if (error) return { statusCode: 500, headers, body: JSON.stringify({ error: error.message }) };
    return { statusCode: 200, headers, body: JSON.stringify({ success: true }) };
  }

  return { statusCode: 405, headers, body: JSON.stringify({ error: 'Method Not Allowed' }) };
};
