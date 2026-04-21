// netlify/functions/credit-scores.js
// GET  /api/credit-scores        → 전체 점수 조회
// PUT  /api/credit-scores        → 점수 수정 { agency, score }

const { createClient } = require('@supabase/supabase-js');

const supabase = createClient(
  process.env.SUPABASE_URL,
  process.env.SUPABASE_SERVICE_KEY
);

exports.handler = async (event) => {
  const headers = {
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods': 'GET, PUT, OPTIONS',
    'Access-Control-Allow-Headers': 'Content-Type',
  };

  if (event.httpMethod === 'OPTIONS') return { statusCode: 204, headers, body: '' };

  if (event.httpMethod === 'GET') {
    const { data, error } = await supabase
      .from('credit_scores')
      .select('agency, score, updated_at')
      .order('agency');
    if (error) return { statusCode: 500, headers, body: JSON.stringify({ error: error.message }) };
    return { statusCode: 200, headers, body: JSON.stringify(data) };
  }

  if (event.httpMethod === 'PUT') {
    const { agency, score } = JSON.parse(event.body || '{}');
    if (!agency || score == null) {
      return { statusCode: 400, headers, body: JSON.stringify({ error: 'agency와 score가 필요합니다' }) };
    }
    const { data, error } = await supabase
      .from('credit_scores')
      .update({ score, updated_at: new Date().toISOString() })
      .eq('agency', agency)
      .select()
      .single();
    if (error) return { statusCode: 500, headers, body: JSON.stringify({ error: error.message }) };
    return { statusCode: 200, headers, body: JSON.stringify(data) };
  }

  return { statusCode: 405, headers, body: JSON.stringify({ error: 'Method Not Allowed' }) };
};
