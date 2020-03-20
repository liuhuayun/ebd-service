// 获取指定键名的数组
export function getTypeArray(list, key) {
  const arr = list.map(item => item[key]);
  return Array.from(new Set(arr));
}
// 从后台数据中获取legend 和 data series
export function getDataByKey(list, key, value) {
  const legend = getTypeArray(list, key);
  const data = [];
  legend.forEach((item) => {
    let itemVal = 0;
    list.forEach((element) => {
      if (element[key] === item) {
        itemVal += element[value];
      }
    });
    data.push(itemVal);
  });
  return { data, legend };
}
// 从小到大排序
export function sortNumber(a, b) {
  return a - b;
}

// 根据数量，宽度，高度自动生成layout数组
// w:宽度 h:高度 n:数量 z:宽度分为多少份
export function computeLayout(w, h, n0, z) {
  const n = parseInt(n0, 10);
  z = z || 12;
  const layout = [];
  for (let i = 0; i < n; i++) {
    const item = {};
    item.i = i;
    item.x = (i % (z / w)) * w;
    item.y = Math.floor(i / (z / w)) * h;
    item.w = w;
    item.h = h;
    layout.push(item);
  }
  return layout;
}
// 根据chartConfig 和 chartData设置图表使用的组件和数据
export function getChartSettings(chartConfig) {
  // 折现图组件需要转一下
  const t = chartConfig.chartType === 'Line' ? 'ELine' : chartConfig.chartType;
  return t === 'StackLine' ? 'MLine' : t;
}

// 日期数据处理，只保留日期
export function getDayFromDatetime(start) {
  const startTime = new Date(start);

  const year = startTime.getFullYear();
  const month = startTime.getMonth() < 9 ? `0${startTime.getMonth() + 1}` : (startTime.getMonth() + 1);
  const day = startTime.getDate() < 10 ? `0${startTime.getDate()}` : startTime.getDate();
  return `${year}-${month}-${day}`;
}

// 获取时间区间内的日期数组 start 2018-12-12
export function getRangeDate(start, end) {
  const startTime = new Date(start);
  const endTime = new Date(end);
  const dateArr = [];
  // 需要包含最后一天
  endTime.setDate(endTime.getDate() + 1);

  while ((endTime.getTime() - startTime.getTime()) > 0) {
    const year = startTime.getFullYear();
    const month = startTime.getMonth() < 9 ? `0${startTime.getMonth() + 1}` : (startTime.getMonth() + 1);
    const day = startTime.getDate() < 10 ? `0${startTime.getDate()}` : startTime.getDate();
    dateArr.push(`${year}-${month}-${day}`);
    startTime.setDate(startTime.getDate() + 1);
  }
  return dateArr;
}
// 获取时间区间内的周数组 start 201801 end 201908
export function getRangeWeek(start, end) {
  let startYear = parseInt(start.slice(0, 4), 10);
  let startWeek = parseInt(start.slice(4), 10);
  const endYear = parseInt(end.slice(0, 4), 10);
  const endWeek = parseInt(end.slice(4), 10);
  const dateArr = [];
  while (endYear - startYear > 0 || (endYear - startYear === 0 && endWeek - startWeek >= 0)) {
    const week = startWeek < 10 ? `0${startWeek}` : `${startWeek}`;
    dateArr.push(`${startYear}${week}`);
    startWeek += 1;
    if (startWeek > 52) {
      startWeek = 1;
      startYear += 1;
    }
  }
  return dateArr;
}
// 获取时间区间内的月数组 start 2018-01 end 2019-08
export function getRangeMonth(start, end) {
  const startTime = new Date(`${start.slice(0, 4)}-${start.slice(5)}-01`);
  console.log(startTime);
  const endTime = new Date(`${end.slice(0, 4)}-${end.slice(5)}-01`);
  console.log(endTime);
  const dateArr = [];
  while ((endTime.getTime() - startTime.getTime()) > 0) {
    const year = startTime.getFullYear();
    const month = startTime.getMonth() < 9 ? `0${startTime.getMonth() + 1}` : (`${startTime.getMonth() + 1}`);
    dateArr.push(`${year}-${month}`);
    startTime.setMonth(startTime.getMonth() + 1);
  }
  dateArr.push(end);
  return dateArr;
}
